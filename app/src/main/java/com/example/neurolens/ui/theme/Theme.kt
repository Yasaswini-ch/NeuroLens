package com.example.neurolens.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.neurolens.data.models.AppTheme

// Zen Garden Colors (now warmer)
private val ZenPrimary = Color(0xFFC97B63)       // Warm Terracotta
private val ZenPrimaryVariant = Color(0xFFA85B4A)   // Darker Terracotta
private val ZenSecondary = Color(0xFFF0D9A5)      // Soft Sand
private val ZenAccent = Color(0xFFF4A261)        // Warm Orange Accent
private val ZenBackground = Color(0xFFFFF7F0)     // Creamy Off-white
private val ZenSurface = Color(0xFFFFFDFC)          // Warmer White

// Cyber Clarity Colors
private val CyberPrimary = Color(0xFF00D9FF)
private val CyberPrimaryVariant = Color(0xFF0099CC)
private val CyberSecondary = Color(0xFFB084FF)
private val CyberAccent = Color(0xFFFF6B9D)
private val CyberBackground = Color(0xFF0A0E27)
private val CyberSurface = Color(0xFF1A1F3A)

// Comic Mode Colors
private val ComicPrimary = Color(0xFFFF6B6B)
private val ComicPrimaryVariant = Color(0xFFEE5A6F)
private val ComicSecondary = Color(0xFF4ECDC4)
private val ComicAccent = Color(0xFFFFE66D)
private val ComicBackground = Color(0xFFFFF8E7)
private val ComicSurface = Color(0xFFFFFFFF)

// Emotion Colors
val EmotionCalm = Color(0xFFA8DADC)
val EmotionFocused = Color(0xFF457B9D)
val EmotionAnxious = Color(0xFFE63946)
val EmotionEnergized = Color(0xFFF4A261)
val EmotionNeutral = Color(0xFFE0E0E0)

val ZenLightColorScheme = lightColorScheme(
    primary = ZenPrimary,
    onPrimary = Color.White,
    primaryContainer = ZenPrimaryVariant,
    secondary = ZenSecondary,
    onSecondary = Color(0xFF432818), // Dark brown for readability
    tertiary = ZenAccent,
    background = ZenBackground,
    onBackground = Color(0xFF432818), // Dark brown
    surface = ZenSurface,
    onSurface = Color(0xFF432818), // Dark brown
    surfaceVariant = Color(0xFFEDEAE6), // Warm light gray
    outline = Color(0xFFDCD8D3) // Warm medium gray
)

val CyberDarkColorScheme = darkColorScheme(
    primary = CyberPrimary,
    onPrimary = Color.Black,
    primaryContainer = CyberPrimaryVariant,
    secondary = CyberSecondary,
    onSecondary = Color.White,
    tertiary = CyberAccent,
    background = CyberBackground,
    onBackground = Color.White,
    surface = CyberSurface,
    onSurface = Color.White,
    surfaceVariant = Color(0xFF2A2F4A),
    outline = Color(0xFF404555)
)

val ComicLightColorScheme = lightColorScheme(
    primary = ComicPrimary,
    onPrimary = Color.White,
    primaryContainer = ComicPrimaryVariant,
    secondary = ComicSecondary,
    onSecondary = Color.White,
    tertiary = ComicAccent,
    background = ComicBackground,
    onBackground = Color(0xFF2C3E50),
    surface = ComicSurface,
    onSurface = Color(0xFF2C3E50),
    surfaceVariant = Color(0xFFFFEFD5),
    outline = Color(0xFFE0C9A6)
)

@Composable
fun NeuroLensTheme(
    theme: AppTheme = AppTheme.ZEN_GARDEN,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when (theme) {
        AppTheme.ZEN_GARDEN -> if (darkTheme) CyberDarkColorScheme else ZenLightColorScheme
        AppTheme.CYBER_CLARITY -> CyberDarkColorScheme
        AppTheme.COMIC_MODE -> ComicLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = NeuroLensTypography,
        shapes = NeuroLensShapes,
        content = content
    )
}
