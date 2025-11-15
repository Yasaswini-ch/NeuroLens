package com.example.neurolens.ui.visualreader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class BackgroundColor {
    CREAM, MINT, BLUE, LAVENDER
}

data class VisualReaderUiState(
    val recognizedText: String = "",
    val summary: String = "",
    val showSummary: Boolean = false,
    val isProcessing: Boolean = false,
    val isSummarizing: Boolean = false,
    val fontSize: Int = 18,
    val lineSpacing: Float = 1.6f,
    val dyslexiaMode: Boolean = true,
    val backgroundColor: BackgroundColor = BackgroundColor.CREAM
)

class VisualReaderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VisualReaderUiState())
    val uiState: StateFlow<VisualReaderUiState> = _uiState.asStateFlow()

    fun scanWithCamera() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isProcessing = true)

            // Simulate camera capture and OCR processing
            delay(2000)

            // In production, this would use the TextRecognizer ML processor
            val sampleText = """
                Welcome to NeuroLens Visual Reader!
                
                This text has been processed with our dyslexia-friendly formatting. You can adjust the font size, line spacing, and background color to suit your reading preferences.
                
                Key features:
                • Increased letter spacing
                • Optimized line height
                • High-contrast backgrounds
                • Text summarization
                
                The Visual Reader uses on-device OCR technology to extract text from images, books, documents, and screens. All processing happens locally on your device, ensuring complete privacy.
                
                Try adjusting the settings above to find what works best for you!
            """.trimIndent()

            _uiState.value = _uiState.value.copy(
                recognizedText = sampleText,
                isProcessing = false,
                showSummary = false
            )
        }
    }

    fun loadSampleText() {
        val sampleText = """
            Understanding Neurodiversity
            
            Neurodiversity recognizes that brain differences are natural variations of the human experience. Rather than viewing conditions like ADHD, autism, and dyslexia as deficits, the neurodiversity paradigm celebrates these differences as valuable contributions to human diversity.
            
            People with different neurological profiles often have unique strengths:
            
            • ADHD: Creativity, hyperfocus, high energy
            • Autism: Pattern recognition, attention to detail, deep expertise
            • Dyslexia: Visual thinking, problem-solving, big-picture perspective
            
            Technology like NeuroLens helps neurodiverse individuals leverage their strengths while providing support in areas that may be challenging. By designing with accessibility in mind from the start, we create tools that benefit everyone.
            
            The future of work and education increasingly recognizes the value of diverse thinking styles and cognitive approaches.
        """.trimIndent()

        _uiState.value = _uiState.value.copy(
            recognizedText = sampleText,
            showSummary = false
        )
    }

    fun summarizeText() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isSummarizing = true)

            // Simulate summarization processing
            delay(1500)

            // Simple extractive summarization (first few sentences)
            val sentences = _uiState.value.recognizedText.split(". ")
            val summary = sentences.take(3).joinToString(". ") + "."

            _uiState.value = _uiState.value.copy(
                summary = "📝 Quick Summary:\n\n$summary\n\n[Full text available above]",
                showSummary = true,
                isSummarizing = false
            )
        }
    }

    fun increaseFontSize() {
        _uiState.value = _uiState.value.copy(
            fontSize = (_uiState.value.fontSize + 2).coerceAtMost(32)
        )
    }

    fun decreaseFontSize() {
        _uiState.value = _uiState.value.copy(
            fontSize = (_uiState.value.fontSize - 2).coerceAtLeast(14)
        )
    }

    fun updateLineSpacing(spacing: Float) {
        _uiState.value = _uiState.value.copy(lineSpacing = spacing)
    }

    fun toggleDyslexiaMode() {
        _uiState.value = _uiState.value.copy(
            dyslexiaMode = !_uiState.value.dyslexiaMode
        )
    }

    fun setBackgroundColor(color: BackgroundColor) {
        _uiState.value = _uiState.value.copy(backgroundColor = color)
    }

    fun speakText() {
        // Would implement text-to-speech
        // For now, just toggle summary view
        _uiState.value = _uiState.value.copy(
            showSummary = !_uiState.value.showSummary
        )
    }

    fun shareText() {
        // Would implement share functionality
    }
}
