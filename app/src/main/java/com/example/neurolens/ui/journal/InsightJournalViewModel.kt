package com.example.neurolens.ui.journal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Insight(
    val title: String,
    val description: String
)

data class InsightJournalUiState(
    val todayMood: Int = 3,
    val todayNotes: String = "",
    val journalStreak: Int = 12,
    val focusStreak: Int = 7,
    val weeklyAverageMood: Float = 4.2f,
    val weeklyFocusMinutes: Int = 420,
    val weeklyDistractions: Int = 15,
    val insights: List<Insight> = listOf(
        Insight(
            "Morning Productivity Peak",
            "You're most focused between 9-11 AM. Schedule deep work during this time."
        ),
        Insight(
            "Positive Mood Trend",
            "Your mood has improved 15% this week. Keep up the great work!"
        ),
        Insight(
            "Distraction Pattern",
            "Most distractions occur after 3 PM. Consider taking short breaks."
        )
    )
)

class InsightJournalViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(InsightJournalUiState())
    val uiState: StateFlow<InsightJournalUiState> = _uiState.asStateFlow()

    fun setTodayMood(mood: Int) {
        _uiState.value = _uiState.value.copy(todayMood = mood)
    }

    fun updateNotes(notes: String) {
        _uiState.value = _uiState.value.copy(todayNotes = notes)
    }

    fun saveEntry() {
        // Would save to local database
        // Reset for next entry
        _uiState.value = _uiState.value.copy(
            todayNotes = "",
            journalStreak = _uiState.value.journalStreak + 1
        )
    }
}
