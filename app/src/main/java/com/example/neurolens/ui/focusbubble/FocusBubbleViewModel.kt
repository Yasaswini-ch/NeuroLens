package com.example.neurolens.ui.focusbubble

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

data class FocusBubbleUiState(
    val isSessionActive: Boolean = false,
    val isPaused: Boolean = false,
    val elapsedSeconds: Int = 0,
    val currentFocusLevel: Float = 1.0f,
    val distractionsCount: Int = 0,
    val dailyStreak: Int = 7
)

class FocusBubbleViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FocusBubbleUiState())
    val uiState: StateFlow<FocusBubbleUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private var distractionJob: Job? = null

    fun startSession() {
        _uiState.value = _uiState.value.copy(
            isSessionActive = true,
            isPaused = false,
            elapsedSeconds = 0,
            distractionsCount = 0
        )
        startTimer()
        startDistractionMonitoring()
    }

    fun pauseSession() {
        val currentState = _uiState.value
        if (currentState.isPaused) {
            // Resume
            _uiState.value = currentState.copy(isPaused = false)
            startTimer()
        } else {
            // Pause
            _uiState.value = currentState.copy(isPaused = true)
            timerJob?.cancel()
        }
    }

    fun stopSession() {
        timerJob?.cancel()
        distractionJob?.cancel()
        _uiState.value = _uiState.value.copy(
            isSessionActive = false,
            isPaused = false
        )
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_uiState.value.isSessionActive && !_uiState.value.isPaused) {
                delay(1000)
                _uiState.value = _uiState.value.copy(
                    elapsedSeconds = _uiState.value.elapsedSeconds + 1
                )

                // Simulate focus level fluctuation
                if (_uiState.value.elapsedSeconds % 10 == 0) {
                    val newFocusLevel = (0.7f + Random.nextFloat() * 0.3f).coerceIn(0f, 1f)
                    _uiState.value = _uiState.value.copy(
                        currentFocusLevel = newFocusLevel
                    )
                }
            }
        }
    }

    private fun startDistractionMonitoring() {
        distractionJob?.cancel()
        distractionJob = viewModelScope.launch {
            while (_uiState.value.isSessionActive) {
                delay(Random.nextLong(30000, 120000)) // Random 30s-2min
                if (_uiState.value.isSessionActive && !_uiState.value.isPaused) {
                    // Simulate distraction detection
                    if (Random.nextFloat() > 0.7f) {
                        _uiState.value = _uiState.value.copy(
                            distractionsCount = _uiState.value.distractionsCount + 1,
                            currentFocusLevel = (_uiState.value.currentFocusLevel * 0.9f).coerceAtLeast(
                                0.5f
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
        distractionJob?.cancel()
    }
}
