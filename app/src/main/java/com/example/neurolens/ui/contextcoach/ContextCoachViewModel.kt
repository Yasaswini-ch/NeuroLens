package com.example.neurolens.ui.contextcoach

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

data class ContextCoachUiState(
    val isMonitoring: Boolean = false,
    val loudnessLevel: Float = 0.5f,
    val speechPace: String = "Normal",
    val conversationFlow: String = "Balanced",
    val currentNudge: String = ""
)

class ContextCoachViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ContextCoachUiState())
    val uiState: StateFlow<ContextCoachUiState> = _uiState.asStateFlow()

    private var monitoringJob: Job? = null

    fun startMonitoring() {
        _uiState.value = _uiState.value.copy(isMonitoring = true)
        startAnalysis()
    }

    fun stopMonitoring() {
        monitoringJob?.cancel()
        _uiState.value = _uiState.value.copy(
            isMonitoring = false,
            currentNudge = ""
        )
    }

    private fun startAnalysis() {
        monitoringJob?.cancel()
        monitoringJob = viewModelScope.launch {
            while (_uiState.value.isMonitoring) {
                delay(3000)

                val loudness = 0.3f + Random.nextFloat() * 0.4f
                val pace = when {
                    Random.nextFloat() > 0.8f -> "Fast"
                    Random.nextFloat() > 0.6f -> "Slow"
                    else -> "Normal"
                }

                val flow = listOf("Balanced", "Active listening", "Good engagement").random()

                val nudge = if (loudness > 0.7f) {
                    "💡 Consider lowering your voice slightly"
                } else if (pace == "Fast") {
                    "💡 Try slowing down your speech pace"
                } else {
                    ""
                }

                _uiState.value = _uiState.value.copy(
                    loudnessLevel = loudness,
                    speechPace = pace,
                    conversationFlow = flow,
                    currentNudge = nudge
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        monitoringJob?.cancel()
    }
}
