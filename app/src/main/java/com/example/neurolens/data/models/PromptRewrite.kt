package com.example.neurolens.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PromptRewrite(
    val originalText: String,
    val tone: WritingTone,
    val rewrittenText: String,
    val timestamp: Long = System.currentTimeMillis()
) : Parcelable

enum class WritingTone(val displayName: String, val description: String) {
    FORMAL(
        "Formal",
        "Professional and structured"
    ),
    FRIENDLY(
        "Friendly",
        "Warm and approachable"
    ),
    ASSERTIVE(
        "Assertive",
        "Clear and confident"
    ),
    SIMPLIFIED(
        "Simplified",
        "Easy to understand"
    ),
    EMPATHETIC(
        "Empathetic",
        "Understanding and supportive"
    ),
    CONCISE(
        "Concise",
        "Brief and to the point"
    )
}

@Parcelize
data class RewriteRequest(
    val text: String,
    val tones: List<WritingTone> = WritingTone.entries.toList()
) : Parcelable
