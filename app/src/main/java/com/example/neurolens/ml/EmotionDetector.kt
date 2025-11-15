package com.example.neurolens.ml

import android.content.Context
import android.graphics.Bitmap
import com.example.neurolens.data.models.Emotion
import com.example.neurolens.data.models.EmotionState
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * On-device emotion detection using ML Kit Face Detection
 * Privacy-first: All processing happens locally
 */
class EmotionDetector : BaseMLProcessor<Bitmap, EmotionState>() {

    private val faceDetector by lazy {
        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .setMinFaceSize(0.15f)
            .enableTracking()
            .build()

        FaceDetection.getClient(options)
    }

    override suspend fun initialize(context: Context) {
        // ML Kit initializes lazily
        initialized = true
    }

    override suspend fun process(input: Bitmap): Result<EmotionState> = runSafely {
        val image = InputImage.fromBitmap(input, 0)
        val faces = suspendCoroutine<List<Face>> { continuation ->
            faceDetector.process(image)
                .addOnSuccessListener { result -> continuation.resume(result) }
                .addOnFailureListener { e -> continuation.resumeWithException(e) }
        }

        if (faces.isEmpty()) {
            EmotionState(
                emotion = Emotion.NEUTRAL,
                confidence = 0f,
                suggestions = listOf("No face detected. Please adjust camera position.")
            )
        } else {
            analyzeFirstFace(faces.first())
        }
    }

    override fun processStream(input: Flow<Bitmap>): Flow<Result<EmotionState>> {
        return input.map { bitmap -> process(bitmap) }
    }

    private fun analyzeFirstFace(face: Face): EmotionState {
        val smilingProb = face.smilingProbability ?: 0f
        val leftEyeOpenProb = face.leftEyeOpenProbability ?: 0.5f
        val rightEyeOpenProb = face.rightEyeOpenProbability ?: 0.5f

        // Determine emotion based on facial features
        val (emotion, confidence) = when {
            smilingProb > 0.7f -> Emotion.HAPPY to smilingProb
            smilingProb > 0.4f -> Emotion.CALM to smilingProb
            leftEyeOpenProb < 0.2f && rightEyeOpenProb < 0.2f -> Emotion.OVERWHELMED to 0.6f
            smilingProb < 0.2f -> Emotion.ANXIOUS to (1f - smilingProb)
            else -> Emotion.NEUTRAL to 0.5f
        }

        val suggestions = generateSuggestions(emotion)

        return EmotionState(
            emotion = emotion,
            confidence = confidence,
            suggestions = suggestions
        )
    }

    private fun generateSuggestions(emotion: Emotion): List<String> {
        return when (emotion) {
            Emotion.ANXIOUS -> listOf(
                "Take a deep breath",
                "Try the 4-7-8 breathing technique",
                "Step away for a short walk"
            )

            Emotion.OVERWHELMED -> listOf(
                "Consider taking a break",
                "Break tasks into smaller steps",
                "Practice grounding exercises"
            )

            Emotion.CALM -> listOf(
                "Great focus! Keep it up",
                "You're in a good flow state"
            )

            Emotion.HAPPY -> listOf(
                "Wonderful energy!",
                "Your positive mood is showing"
            )

            Emotion.FOCUSED -> listOf(
                "Excellent concentration",
                "You're in deep work mode"
            )

            else -> listOf(
                "How are you feeling?",
                "Check in with yourself"
            )
        }
    }

    override fun release() {
        faceDetector.close()
        initialized = false
    }
}
