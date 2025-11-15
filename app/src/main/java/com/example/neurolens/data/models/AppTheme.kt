package com.example.neurolens.data.models

enum class AppTheme(val displayName: String, val description: String) {
    ZEN_GARDEN(
        "Zen Garden",
        "Earthy and warm, inspired by terracotta and sand tones."
    ),
    CYBER_CLARITY(
        "Cyber Clarity",
        "High contrast, modern with neon accents"
    ),
    COMIC_MODE(
        "Comic Mode",
        "Playful and energizing with bright colors"
    )
}

data class AccessibilitySettings(
    val vibrationEnabled: Boolean = true,
    val audioCuesEnabled: Boolean = true,
    val visualNudgesEnabled: Boolean = true,
    val dyslexiaFontEnabled: Boolean = false,
    val increasedSpacing: Boolean = false,
    val highContrast: Boolean = false,
    val fontSize: Float = 1.0f,
    val animationSpeed: Float = 1.0f
)

data class UserPreferences(
    val theme: AppTheme = AppTheme.ZEN_GARDEN,
    val accessibility: AccessibilitySettings = AccessibilitySettings(),
    val onboardingCompleted: Boolean = false,
    val permissionsGranted: PermissionStatus = PermissionStatus()
)

data class PermissionStatus(
    val camera: Boolean = false,
    val microphone: Boolean = false,
    val notifications: Boolean = false
)
