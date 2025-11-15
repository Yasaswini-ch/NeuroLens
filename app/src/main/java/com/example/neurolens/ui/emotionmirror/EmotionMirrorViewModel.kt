package com.example.neurolens.ui.emotionmirror

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neurolens.data.models.EmotionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class EmotionMirrorUiState(
    val isAnalyzing: Boolean = false,
    val currentEmotion: EmotionState? = null,
    val error: String? = null
)

class EmotionMirrorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EmotionMirrorUiState())
    val uiState: StateFlow<EmotionMirrorUiState> = _uiState.asStateFlow()

    fun startAnalysis() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isAnalyzing = true)
            // In production, this would start camera and emotion detection
            // For now, we'll simulate with placeholder
        }
    }

    fun stopAnalysis() {
        _uiState.value = _uiState.value.copy(
            isAnalyzing = false,
            currentEmotion = null
        )
    }

    override fun onCleared() {
        super.onCleared()
        stopAnalysis()
    }
}
