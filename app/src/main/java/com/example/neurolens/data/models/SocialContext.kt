package com.example.neurolens.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SocialContext(
    val loudnessLevel: Float = 0f, // 0-1 range
    val speechPace: SpeechPace = SpeechPace.NORMAL,
    val facialReactions: List<FacialReaction> = emptyList(),
    val interruptionDetected: Boolean = false,
    val conversationFlow: ConversationFlow = ConversationFlow.BALANCED,
    val timestamp: Long = System.currentTimeMillis(),
    val nudges: List<Nudge> = emptyList()
) : Parcelable

enum class SpeechPace {
    TOO_SLOW,
    SLOW,
    NORMAL,
    FAST,
    TOO_FAST
}

enum class FacialReaction {
    SMILING,
    FROWNING,
    CONFUSED,
    NEUTRAL,
    ENGAGED,
    DISENGAGED
}

enum class ConversationFlow {
    BALANCED,
    DOMINATING,
    PASSIVE,
    INTERRUPTED
}

@Parcelize
data class Nudge(
    val type: NudgeType,
    val message: String,
    val intensity: Float = 0.5f,
    val timestamp: Long = System.currentTimeMillis()
) : Parcelable

enum class NudgeType {
    VIBRATION,
    VISUAL,
    AUDIO,
    COMBINED
}
