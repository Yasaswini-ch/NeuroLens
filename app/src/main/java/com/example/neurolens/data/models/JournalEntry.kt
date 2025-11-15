package com.example.neurolens.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JournalEntry(
    val id: String,
    val date: Long,
    val moodRating: Int, // 1-10 scale
    val emotions: List<Emotion>,
    val focusScore: Float,
    val socialInteractions: Int,
    val notes: String = "",
    val achievements: List<String> = emptyList(),
    val challenges: List<String> = emptyList()
) : Parcelable

@Parcelize
data class InsightStreak(
    val type: StreakType,
    val currentStreak: Int,
    val longestStreak: Int,
    val lastUpdated: Long
) : Parcelable

enum class StreakType(val displayName: String) {
    DAILY_JOURNAL("Daily Journal"),
    FOCUS_SESSIONS("Focus Sessions"),
    EMOTION_CHECKS("Emotion Checks"),
    PROMPT_REWRITES("Prompt Rewrites"),
    VISUAL_READING("Visual Reading")
}

@Parcelize
data class InsightPattern(
    val category: PatternCategory,
    val description: String,
    val confidence: Float,
    val recommendation: String,
    val detectedAt: Long
) : Parcelable

enum class PatternCategory {
    MOOD_TREND,
    FOCUS_PATTERN,
    SOCIAL_PATTERN,
    ENERGY_LEVEL,
    PRODUCTIVITY
}
