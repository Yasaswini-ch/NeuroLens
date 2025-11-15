package com.example.neurolens.ui.promptrewriter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neurolens.data.models.PromptRewrite
import com.example.neurolens.data.models.WritingTone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PromptRewriterUiState(
    val inputText: String = "",
    val selectedTone: WritingTone? = null,
    val rewrites: List<PromptRewrite> = emptyList(),
    val isProcessing: Boolean = false,
    val error: String? = null
)

class PromptRewriterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PromptRewriterUiState())
    val uiState: StateFlow<PromptRewriterUiState> = _uiState.asStateFlow()

    fun updateInputText(text: String) {
        _uiState.value = _uiState.value.copy(inputText = text)
    }

    fun clearInput() {
        _uiState.value = _uiState.value.copy(
            inputText = "",
            rewrites = emptyList()
        )
    }

    fun selectTone(tone: WritingTone) {
        _uiState.value = _uiState.value.copy(selectedTone = tone)
        if (_uiState.value.inputText.isNotBlank()) {
            rewriteSingle(tone)
        }
    }

    fun rewriteAll() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isProcessing = true)

            // Simulate rewriting process
            // In production, this would use the PromptRewriteEngine
            val rewrites = WritingTone.entries.map { tone ->
                PromptRewrite(
                    originalText = _uiState.value.inputText,
                    tone = tone,
                    rewrittenText = simulateRewrite(_uiState.value.inputText, tone)
                )
            }

            _uiState.value = _uiState.value.copy(
                rewrites = rewrites,
                isProcessing = false
            )
        }
    }

    private fun rewriteSingle(tone: WritingTone) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isProcessing = true)

            val rewrite = PromptRewrite(
                originalText = _uiState.value.inputText,
                tone = tone,
                rewrittenText = simulateRewrite(_uiState.value.inputText, tone)
            )

            val updatedRewrites = _uiState.value.rewrites
                .filter { it.tone != tone } + rewrite

            _uiState.value = _uiState.value.copy(
                rewrites = updatedRewrites,
                isProcessing = false
            )
        }
    }

    private fun simulateRewrite(text: String, tone: WritingTone): String {
        // Simple simulation - in production would use PromptRewriteEngine
        return when (tone) {
            WritingTone.FORMAL -> "Dear recipient,\n\n$text\n\nSincerely"
            WritingTone.FRIENDLY -> "Hey! $text 😊"
            WritingTone.ASSERTIVE -> text.replace("?", ".").replace("maybe", "").trim()
            WritingTone.SIMPLIFIED -> text.split(".").firstOrNull()?.trim() ?: text
            WritingTone.EMPATHETIC -> "I understand. $text"
            WritingTone.CONCISE -> text.take(100) + if (text.length > 100) "..." else ""
        }
    }

    fun copyToClipboard(text: String) {
        // Would implement clipboard functionality
    }

    fun shareText(text: String) {
        // Would implement share functionality
    }
}
