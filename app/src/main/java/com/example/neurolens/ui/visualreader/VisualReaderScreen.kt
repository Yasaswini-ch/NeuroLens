package com.example.neurolens.ui.visualreader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.neurolens.ui.components.NeuroCard

@Composable
fun VisualReaderScreen(
    viewModel: VisualReaderViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "Visual Overlay Reader",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Dyslexia-friendly text processing with OCR",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Camera/Upload Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { viewModel.scanWithCamera() },
                modifier = Modifier.weight(1f),
                enabled = !uiState.isProcessing
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Scan Text")
            }

            OutlinedButton(
                onClick = { viewModel.loadSampleText() },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Sample")
            }
        }

        // Processing Indicator
        if (uiState.isProcessing) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Formatting Controls
        if (uiState.recognizedText.isNotEmpty()) {
            NeuroCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Reading Settings",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    // Font Size
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Font Size: ${uiState.fontSize}sp")
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            IconButton(onClick = { viewModel.decreaseFontSize() }) {
                                Icon(Icons.Default.Remove, "Decrease")
                            }
                            IconButton(onClick = { viewModel.increaseFontSize() }) {
                                Icon(Icons.Default.Add, "Increase")
                            }
                        }
                    }

                    // Line Spacing
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Line Spacing: ${String.format("%.1f", uiState.lineSpacing)}x")
                        Slider(
                            value = uiState.lineSpacing,
                            onValueChange = { viewModel.updateLineSpacing(it) },
                            valueRange = 1.0f..2.5f,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp)
                        )
                    }

                    // Background Color
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Background:", modifier = Modifier.align(Alignment.CenterVertically))

                        FilterChip(
                            selected = uiState.backgroundColor == BackgroundColor.CREAM,
                            onClick = { viewModel.setBackgroundColor(BackgroundColor.CREAM) },
                            label = { Text("Cream") }
                        )
                        FilterChip(
                            selected = uiState.backgroundColor == BackgroundColor.MINT,
                            onClick = { viewModel.setBackgroundColor(BackgroundColor.MINT) },
                            label = { Text("Mint") }
                        )
                        FilterChip(
                            selected = uiState.backgroundColor == BackgroundColor.BLUE,
                            onClick = { viewModel.setBackgroundColor(BackgroundColor.BLUE) },
                            label = { Text("Blue") }
                        )
                    }

                    // Dyslexia Mode Toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Dyslexia-Friendly Font",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "OpenDyslexic style",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Switch(
                            checked = uiState.dyslexiaMode,
                            onCheckedChange = { viewModel.toggleDyslexiaMode() }
                        )
                    }
                }
            }
        }

        // Recognized Text Display
        if (uiState.recognizedText.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.summarizeText() },
                    modifier = Modifier.weight(1f),
                    enabled = !uiState.isSummarizing
                ) {
                    Icon(Icons.Default.Compress, null, Modifier.padding(end = 8.dp))
                    Text("Summarize")
                }

                IconButton(onClick = { viewModel.speakText() }) {
                    Icon(Icons.Default.VolumeUp, "Read aloud")
                }

                IconButton(onClick = { viewModel.shareText() }) {
                    Icon(Icons.Default.Share, "Share")
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = getBackgroundColor(uiState.backgroundColor)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = if (uiState.showSummary && uiState.summary.isNotEmpty()) {
                            "Summary:\n\n${uiState.summary}"
                        } else {
                            uiState.recognizedText
                        },
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = uiState.fontSize.sp,
                            lineHeight = (uiState.fontSize * uiState.lineSpacing).sp,
                            letterSpacing = if (uiState.dyslexiaMode) 0.5.sp else 0.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        } else if (!uiState.isProcessing) {
            // Empty State
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.DocumentScanner,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Scan text to get started",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Use the camera to capture text from books, documents, or screens",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun getBackgroundColor(backgroundColor: BackgroundColor): androidx.compose.ui.graphics.Color {
    return when (backgroundColor) {
        BackgroundColor.CREAM -> androidx.compose.ui.graphics.Color(0xFFFFF9E6)
        BackgroundColor.MINT -> androidx.compose.ui.graphics.Color(0xFFE8F5E9)
        BackgroundColor.BLUE -> androidx.compose.ui.graphics.Color(0xFFE3F2FD)
        BackgroundColor.LAVENDER -> androidx.compose.ui.graphics.Color(0xFFF3E5F5)
    }
}
