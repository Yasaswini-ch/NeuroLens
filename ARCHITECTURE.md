# NeuroLens Architecture

## Overview

NeuroLens follows **Clean Architecture** principles with **MVVM** (Model-View-ViewModel) pattern,
optimized for offline-first, on-device ML processing for neurodiverse users.

## Architecture Layers

```
┌─────────────────────────────────────────────────┐
│              UI Layer (Jetpack Compose)         │
│  ┌──────────────┐  ┌──────────────┐            │
│  │   Screens    │  │  Components  │            │
│  └──────────────┘  └──────────────┘            │
└────────────────┬────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────┐
│           ViewModel Layer                       │
│  ┌──────────────────────────────────┐          │
│  │      State Management            │          │
│  │  (StateFlow, MutableStateFlow)   │          │
│  └──────────────────────────────────┘          │
└────────────────┬────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────┐
│            Domain Layer                         │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐     │
│  │ Use Cases│  │  Models  │  │Repository│     │
│  └──────────┘  └──────────┘  └──────────┘     │
└────────────────┬──────────────────────���─────────┘
                 │
┌────────────────▼────────────────────────────────┐
│             Data Layer                          │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐     │
│  │ ML Kit   │  │DataStore │  │  Local   │     │
│  │          │  │          │  │   DB     │     │
│  └──────────┘  └──────────┘  └──────────┘     │
└─────────────────────────────────────────────────┘
```

## Project Structure

```
com.example.neurolens/
├── data/
│   ├── models/              # Data models and entities
│   │   ├── AppTheme.kt
│   │   ├── EmotionState.kt
│   │   ├── FocusSession.kt
│   │   ├── JournalEntry.kt
│   │   ├── PromptRewrite.kt
│   │   └── SocialContext.kt
│   ├── repository/          # Data access layer (future)
│   └── local/               # Local storage (future)
│
├── ml/                      # On-device ML processors
│   ├── EmotionDetector.kt   # Face detection & emotion analysis
│   ├── TextRecognizer.kt    # OCR for Visual Reader
│   └── PromptRewriteEngine.kt # Text transformation
│
├── ui/
│   ├── theme/               # Material 3 theming
│   │   ├── Theme.kt         # Color schemes (3 variants)
│   │   ├── Type.kt          # Typography definitions
│   │   └── Shape.kt         # Shape definitions
│   │
│   ├── components/          # Reusable UI components
│   │   ├── NeuroCard.kt     # Accessible card component
│   │   ├── ModuleCard.kt    # Feature module cards
│   │   └── FocusPulseAnimation.kt # Breathing animation
│   │
│   ├── home/                # Home screen
│   │   └── HomeScreen.kt
│   │
│   ├── emotionmirror/       # Emotion Mirror module
│   │   └── EmotionMirrorScreen.kt
│   │
│   ├── promptrewriter/      # Smart Prompt Rewriter
│   │   ├── PromptRewriterScreen.kt
│   │   └── PromptRewriterViewModel.kt
│   │
│   ├── focusbubble/         # Focus Bubble module
│   │   └── FocusBubbleScreen.kt
│   │
│   ├── visualreader/        # Visual Reader module
│   │   └── VisualReaderScreen.kt
│   │
│   ├── contextcoach/        # Live Context Coach
│   │   └── ContextCoachScreen.kt
│   │
│   └── journal/             # Insight Journal
│       └── InsightJournalScreen.kt
│
└── MainActivity.kt          # Entry point & navigation

```

## Technology Stack

### Core

- **Language:** Kotlin 2.0.21
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 36 (Android 14+)
- **Build System:** Gradle (Kotlin DSL)

### UI Framework

- **Jetpack Compose:** Modern declarative UI
- **Material 3:** Latest design system
- **Navigation Compose:** Type-safe navigation
- **ViewModel:** State management with lifecycle awareness

### ML & AI

- **ML Kit Face Detection:** Real-time emotion analysis
- **ML Kit Text Recognition:** OCR for Visual Reader
- **TensorFlow Lite:** Future on-device LLM integration
- **Audio Processing:** Speech analysis for Context Coach

### Data & Storage

- **DataStore:** Preferences and settings
- **Room (future):** Structured local database
- **Kotlin Coroutines:** Asynchronous operations
- **Flow:** Reactive data streams

### Camera & Sensors

- **CameraX:** Modern camera API
- **Motion Sensors:** Distraction detection

## Design Patterns

### 1. MVVM (Model-View-ViewModel)

- **View:** Composable functions (UI)
- **ViewModel:** Business logic and state management
- **Model:** Data models and repositories

### 2. Unidirectional Data Flow (UDF)

```kotlin
UI Event → ViewModel → State Update → UI Recomposition
```

### 3. State Management

```kotlin
data class UiState(
    val data: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
}
```

### 4. Repository Pattern (Future)

```kotlin
interface Repository {
    suspend fun getData(): Flow<Result<Data>>
    suspend fun saveData(data: Data): Result<Unit>
}
```

## Key Components

### Theme System

Three carefully designed themes for different needs:

#### Zen Garden (Default)

- Warm, earthy colors (terracotta, sand)
- Calm and nature-inspired
- Best for general use and relaxation

#### Cyber Clarity

- High contrast, dark theme
- Neon accents on dark backgrounds
- Best for focus sessions and low-light

#### Comic Mode

- Bright, saturated colors
- Playful and energizing
- Best for motivation and engagement

### Navigation

```kotlin
sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "NeuroLens")
    object ContextCoach : Screen("context_coach", "Context Coach")
    object PromptRewriter : Screen("prompt_rewriter", "Prompt Rewriter")
    object FocusBubble : Screen("focus_bubble", "Focus Bubble")
    object VisualReader : Screen("visual_reader", "Visual Reader")
    object EmotionMirror : Screen("emotion_mirror", "Emotion Mirror")
    object Journal : Screen("journal", "Insight Journal")
    object Settings : Screen("settings", "Settings")
}
```

## ML Processing Pipeline

### Emotion Detection

```
Camera → CameraX → ML Kit Face Detection → EmotionDetector → UI
```

### Text Recognition

```
Camera → CameraX → ML Kit Text Recognition → TextRecognizer → UI
```

### Prompt Rewriting

```
Input Text → PromptRewriteEngine → Transformed Text → UI
```

## Privacy & Security

### On-Device Processing

- **Zero Cloud Dependency:** All ML processing happens locally
- **No Data Collection:** No user data leaves the device
- **No Analytics:** No tracking or telemetry
- **Camera Access:** Only when explicitly requested by user
- **Microphone Access:** Only for Context Coach feature

### Data Storage

- All data stored locally in app's private directory
- DataStore for encrypted preferences
- Optional backup via Android Auto Backup (user-controlled)

## Accessibility Features

### Built-in Accessibility

- High contrast mode support
- Dyslexia-friendly fonts
- Generous spacing and touch targets
- WCAG AAA compliance
- Screen reader support
- Haptic feedback options

### Neurodiversity-Specific

- Gentle animations (adjustable speed)
- Soft color schemes
- Clear visual hierarchy
- Minimal cognitive load
- Non-intrusive notifications

## Performance Considerations

### Memory Management

- Efficient Compose recomposition
- Proper lifecycle awareness
- ML model caching
- Image optimization

### Battery Optimization

- WorkManager for background tasks
- Efficient camera usage
- Conditional ML processing
- Dark theme options

## Testing Strategy

### Unit Tests

- ViewModel logic
- ML processing algorithms
- Data transformations
- Business rules

### UI Tests

- Screen navigation
- User interactions
- Accessibility compliance
- Theme switching

### Integration Tests

- ML Kit integration
- Camera functionality
- Data persistence
- Permission handling

## Future Enhancements

### Phase 2

- Room database for structured data
- Repository pattern implementation
- Advanced ML models
- Wearable integration

### Phase 3

- Offline LLM integration (RunAnywhere SDK)
- Voice input/output
- Multi-language support
- Custom theme builder
- Advanced analytics (local only)

## Dependencies Management

See `gradle/libs.versions.toml` for version catalog.

Key dependencies:

- Jetpack Compose BOM: 2024.11.00
- ML Kit Face Detection: 16.1.7
- ML Kit Text Recognition: 16.0.1
- TensorFlow Lite: 2.14.0
- CameraX: 1.3.4
- Navigation Compose: 2.8.5
- DataStore: 1.1.1

## Build Configuration

### Debug Build

- Enables debug logging
- No code obfuscation
- Faster build times

### Release Build

- ProGuard enabled
- Code obfuscation
- Optimized for size and performance

## Contributing

When adding new features:

1. Follow existing architecture patterns
2. Maintain MVVM separation
3. Keep UI logic in Composables
4. Keep business logic in ViewModels
5. Ensure offline-first functionality
6. Add appropriate documentation
7. Write tests for new features

---

**Architecture Version:** 1.0  
**Last Updated:** November 2024  
**Maintainer:** NeuroLens Team