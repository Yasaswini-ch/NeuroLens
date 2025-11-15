package com.example.neurolens.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FocusSession(
    val id: String,
    val startTime: Long,
    val endTime: Long? = null,
    val focusLevel: Float = 1.0f, // 0-1 range
    val distractionsDetected: Int = 0,
    val deepWorkMinutes: Int = 0,
    val breaks: List<Break> = emptyList(),
    val isActive: Boolean = true
) : Parcelable {
    val duration: Long
        get() = (endTime ?: System.currentTimeMillis()) - startTime

    val focusScore: Float
        get() = if (duration > 0) {
            (deepWorkMinutes * 60000f / duration) * focusLevel
        } else 0f
}

@Parcelize
data class Break(
    val startTime: Long,
    val duration: Long,
    val type: BreakType
) : Parcelable

enum class BreakType {
    SHORT_BREAK,
    LONG_BREAK,
    DISTRACTION
}

@Parcelize
data class DistractionEvent(
    val timestamp: Long,
    val type: DistractionType,
    val duration: Long = 0
) : Parcelable

enum class DistractionType {
    PHONE_CHECK,
    CONVERSATION,
    MOVEMENT,
    MULTITASKING,
    UNKNOWN
}
