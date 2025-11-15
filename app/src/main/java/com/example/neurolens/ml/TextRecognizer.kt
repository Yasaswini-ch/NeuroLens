package com.example.neurolens.ml

import android.content.Context
import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * On-device text recognition using ML Kit
 * Extracts text from images for accessibility features
 */
class TextRecognizer : BaseMLProcessor<Bitmap, String>() {

    private val recognizer by lazy {
        TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    }

    override suspend fun initialize(context: Context) {
        initialized = true
    }

    override suspend fun process(input: Bitmap): Result<String> = runSafely {
        val image = InputImage.fromBitmap(input, 0)
        val result = suspendCoroutine<Text> { continuation ->
            recognizer.process(image)
                .addOnSuccessListener { text -> continuation.resume(text) }
                .addOnFailureListener { e -> continuation.resumeWithException(e) }
        }

        extractText(result)
    }

    override fun processStream(input: Flow<Bitmap>): Flow<Result<String>> {
        return input.map { bitmap -> process(bitmap) }
    }

    private fun extractText(visionText: Text): String {
        val blocks = visionText.textBlocks
        return if (blocks.isEmpty()) {
            ""
        } else {
            blocks.joinToString("\n\n") { block ->
                block.lines.joinToString("\n") { it.text }
            }
        }
    }

    /**
     * Extract text with formatting information
     */
    suspend fun processWithFormatting(bitmap: Bitmap): Result<FormattedText> = runSafely {
        val image = InputImage.fromBitmap(bitmap, 0)
        val result = suspendCoroutine<Text> { continuation ->
            recognizer.process(image)
                .addOnSuccessListener { text -> continuation.resume(text) }
                .addOnFailureListener { e -> continuation.resumeWithException(e) }
        }

        FormattedText(
            rawText = result.text,
            blocks = result.textBlocks.map { block ->
                TextBlock(
                    text = block.text,
                    lines = block.lines.map { line -> line.text },
                    boundingBox = block.boundingBox
                )
            }
        )
    }

    override fun release() {
        recognizer.close()
        initialized = false
    }
}

data class FormattedText(
    val rawText: String,
    val blocks: List<TextBlock>
)

data class TextBlock(
    val text: String,
    val lines: List<String>,
    val boundingBox: android.graphics.Rect?
)
