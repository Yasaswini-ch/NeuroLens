package com.example.neurolens.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmotionState(
    val emotion: Emotion,
    val confidence: Float,
    val timestamp: Long = System.currentTimeMillis(),
    val suggestions: List<String> = emptyList()
) : Parcelable

enum class Emotion(val displayName: String, val color: String) {
    CALM("Calm", "#A8DADC"),
    FOCUSED("Focused", "#457B9D"),
    ANXIOUS("Anxious", "#E63946"),
    ENERGIZED("Energized", "#F4A261"),
    NEUTRAL("Neutral", "#E0E0E0"),
    HAPPY("Happy", "#4ECDC4"),
    CONFUSED("Confused", "#FFE66D"),
    OVERWHELMED("Overwhelmed", "#E76F51")
}
