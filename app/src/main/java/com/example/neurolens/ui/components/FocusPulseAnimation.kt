package com.example.neurolens.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

/**
 * Focus Pulse Animation
 * A subtle, growing/shrinking circle that indicates deep focus state
 * Designed to be non-distracting and calming
 */
@Composable
fun FocusPulseAnimation(
    isActive: Boolean,
    focusLevel: Float = 1.0f, // 0.0 to 1.0
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF4A90A4)
) {
    if (!isActive) return

    val infiniteTransition = rememberInfiniteTransition(label = "focus_pulse")

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (2000 / focusLevel).toInt().coerceIn(1000, 3000),
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (2000 / focusLevel).toInt().coerceIn(1000, 3000),
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_alpha"
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size((60 * scale).dp)
                .fillMaxSize()
        ) {
            drawCircle(
                color = color.copy(alpha = alpha * focusLevel),
                radius = size.minDimension / 2,
                style = Stroke(width = 4.dp.toPx())
            )
        }
    }
}

/**
 * Gentle breathing animation for calming exercises
 */
@Composable
fun BreathingCircle(
    isActive: Boolean,
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF8BB8A8)
) {
    if (!isActive) return

    val infiniteTransition = rememberInfiniteTransition(label = "breathing")

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 4000, // 4 seconds inhale
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breathing_scale"
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size((150 * scale).dp)
                .fillMaxSize()
        ) {
            drawCircle(
                color = color.copy(alpha = 0.3f),
                radius = size.minDimension / 2
            )
            drawCircle(
                color = color.copy(alpha = 0.6f),
                radius = size.minDimension / 2,
                style = Stroke(width = 2.dp.toPx())
            )
        }
    }
}
