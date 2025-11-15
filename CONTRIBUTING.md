# Contributing to NeuroLens

First off, thank you for considering contributing to NeuroLens! 🎉

NeuroLens is a privacy-first cognitive companion designed specifically for neurodiverse users. We
welcome contributions that help make neurodiversity a superpower!

## 🌟 Ways to Contribute

### 1. Code Contributions

- Bug fixes
- New features
- Performance improvements
- UI/UX enhancements
- Accessibility improvements

### 2. Non-Code Contributions

- Documentation improvements
- Translation/Localization
- UI/UX design
- Testing and bug reports
- Feature suggestions
- Community support

### 3. Accessibility Testing

- Testing with screen readers
- Testing with different accessibility settings
- Feedback from neurodiverse users

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 24+ (Android 7.0+)
- Kotlin 1.9+
- Git

### Setting Up Development Environment

1. **Fork the repository** on GitHub

2. **Clone your fork:**
   ```bash
   git clone https://github.com/YOUR-USERNAME/NeuroLens.git
   cd NeuroLens
   ```

3. **Add upstream remote:**
   ```bash
   git remote add upstream https://github.com/Yasaswini-ch/NeuroLens.git
   ```

4. **Open in Android Studio:**
   ```bash
   open -a "Android Studio" .
   ```

5. **Sync Gradle** and wait for dependencies to download

6. **Run the app** on an emulator or device

## 📝 Development Workflow

### 1. Create a Branch

```bash
# Update your main branch
git checkout main
git pull upstream main

# Create a feature branch
git checkout -b feature/your-feature-name
```

Branch naming conventions:

- `feature/feature-name` - New features
- `fix/bug-name` - Bug fixes
- `docs/description` - Documentation changes
- `refactor/description` - Code refactoring
- `test/description` - Test additions or changes
- `ui/description` - UI/UX improvements

### 2. Make Your Changes

- Write clean, readable code
- Follow the existing code style
- Add comments for complex logic
- Update documentation if needed

### 3. Test Your Changes

```bash
# Run unit tests
./gradlew test

# Run UI tests
./gradlew connectedAndroidTest

# Run lint checks
./gradlew lint
```

### 4. Commit Your Changes

Follow [Conventional Commits](https://www.conventionalcommits.org/):

```bash
git add .
git commit -m "feat: add emotion detection calibration

- Add calibration screen for emotion detection
- Improve accuracy with user-specific baselines
- Add progress indicator for calibration

Closes #123"
```

Commit message format:

- `feat:` - New feature
- `fix:` - Bug fix
- `docs:` - Documentation changes
- `style:` - Code style changes (formatting, etc.)
- `refactor:` - Code refactoring
- `test:` - Adding or updating tests
- `chore:` - Maintenance tasks

### 5. Push to Your Fork

```bash
git push origin feature/your-feature-name
```

### 6. Create a Pull Request

1. Go to your fork on GitHub
2. Click "Compare & pull request"
3. Fill in the PR template:
    - **Title:** Clear and descriptive
    - **Description:** Explain what and why
    - **Screenshots:** If UI changes
    - **Testing:** How you tested
    - **Related Issues:** Link to issues

## 💻 Code Style Guidelines

### Kotlin Style

Follow the [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html):

```kotlin
// Good
fun calculateEmotionScore(faces: List<Face>): Float {
    return faces
        .mapNotNull { it.smilingProbability }
        .average()
        .toFloat()
}

// Bad
fun calc(f: List<Face>): Float {
    var sum = 0f
    for(face in f) {
        sum += face.smilingProbability ?: 0f
    }
    return sum / f.size
}
```

### Jetpack Compose Guidelines

```kotlin
// Good - Clear, composable, reusable
@Composable
fun EmotionCard(
    emotion: EmotionState,
    onTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    NeuroCard(
        onClick = onTap,
        modifier = modifier
    ) {
        // Content
    }
}

// Bad - Too complex, not reusable
@Composable
fun Screen() {
    Column {
        Card(onClick = {
            // Complex logic here
        }) {
            // Lots of nested content
        }
    }
}
```

### MVVM Pattern

```kotlin
// ViewModel
class FeatureViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    
    fun onEvent(event: UiEvent) {
        // Handle events
    }
}

// Screen
@Composable
fun FeatureScreen(
    viewModel: FeatureViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // UI based on state
}
```

## 🧪 Testing Guidelines

### Unit Tests

```kotlin
@Test
fun `emotion detection returns correct emotion`() {
    // Given
    val face = createMockFace(smilingProbability = 0.9f)
    
    // When
    val emotion = emotionDetector.detect(face)
    
    // Then
    assertEquals(Emotion.HAPPY, emotion)
}
```

### UI Tests

```kotlin
@Test
fun clickingEmotionMirrorNavigatesToScreen() {
    composeTestRule.setContent {
        NeuroLensApp()
    }
    
    composeTestRule
        .onNodeWithText("Emotion Mirror")
        .performClick()
    
    composeTestRule
        .onNodeWithText("Emotion Mirror")
        .assertIsDisplayed()
}
```

## 🎨 Design Guidelines

### Material 3 Theme

- Use `MaterialTheme.colorScheme` for colors
- Use `MaterialTheme.typography` for text styles
- Use `MaterialTheme.shapes` for shapes
- Follow the 3 theme variants: Zen Garden, Cyber Clarity, Comic Mode

### Accessibility

**CRITICAL:** All contributions must maintain accessibility:

- ✅ WCAG AAA compliance
- ✅ Screen reader support
- ✅ High contrast mode
- ✅ Generous touch targets (48dp minimum)
- ✅ Clear visual hierarchy
- ✅ Descriptive content descriptions
- ✅ Keyboard navigation support

```kotlin
// Good
Icon(
    imageVector = Icons.Default.Settings,
    contentDescription = "Open settings"
)

// Bad
Icon(
    imageVector = Icons.Default.Settings,
    contentDescription = null
)
```

### Animations

Keep animations:

- Gentle and non-intrusive
- Adjustable speed (respect user preferences)
- Meaningful and purposeful
- Cancellable

## 📚 Documentation

- Update README.md for user-facing changes
- Update ARCHITECTURE.md for architectural changes
- Add KDoc comments for public APIs
- Include code examples in documentation

```kotlin
/**
 * Detects emotions from facial features using ML Kit.
 *
 * @param face The detected face from ML Kit
 * @return The detected [EmotionState] with confidence scores
 */
fun detectEmotion(face: Face): EmotionState
```

## 🐛 Reporting Bugs

Use the GitHub issue template:

**Title:** Clear and specific

**Description:**

- What happened
- What you expected
- Steps to reproduce
- Screenshots/videos if applicable
- Device and Android version
- App version

## 💡 Suggesting Features

- Check existing issues first
- Explain the use case
- Consider neurodiversity needs
- Think about privacy implications
- Provide mockups if possible

## 🔒 Privacy Considerations

**NEVER:**

- Add analytics or tracking
- Send data to external servers
- Add dependencies that phone home
- Store sensitive data unencrypted
- Request unnecessary permissions

**ALWAYS:**

- Process data on-device
- Respect user privacy
- Be transparent about data usage
- Follow GDPR/privacy best practices

## 🌐 Internationalization

When adding strings:

```xml
<!-- strings.xml -->
<string name="feature_title">Title</string>
<string name="feature_description">Description</string>
```

Never hardcode strings in code:

```kotlin
// Good
Text(stringResource(R.string.feature_title))

// Bad
Text("Title")
```

## 📋 Pull Request Checklist

Before submitting:

- [ ] Code follows project style guidelines
- [ ] All tests pass
- [ ] New tests added for new features
- [ ] Documentation updated
- [ ] No lint warnings
- [ ] Accessibility maintained/improved
- [ ] Privacy principles upheld
- [ ] Screenshots added for UI changes
- [ ] Commits follow conventional commits
- [ ] Branch is up to date with main

## 🎓 Resources

- [Android Development](https://developer.android.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Docs](https://kotlinlang.org/docs/home.html)
- [Material 3 Guidelines](https://m3.material.io/)
- [WCAG Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)

## 🤝 Code of Conduct

### Our Pledge

We pledge to make participation in NeuroLens a harassment-free experience for everyone, regardless
of:

- Neurodiversity status
- Age, body size, disability
- Ethnicity, gender identity and expression
- Level of experience
- Nationality
- Personal appearance, race, religion
- Sexual identity and orientation

### Our Standards

**Positive behaviors:**

- Using welcoming and inclusive language
- Being respectful of differing viewpoints
- Gracefully accepting constructive criticism
- Focusing on what's best for the community
- Showing empathy towards others

**Unacceptable behaviors:**

- Harassment, trolling, insulting comments
- Public or private harassment
- Publishing others' private information
- Other unprofessional conduct

### Enforcement

Report violations to: cheboluyasaswini@gmail.com

## 📞 Questions?

- Open an issue with the "question" label
- Email: cheboluyasaswini@gmail.com
- Discussions: GitHub Discussions tab

## 🙏 Recognition

Contributors will be:

- Listed in CONTRIBUTORS.md
- Mentioned in release notes
- Credited in the app's About section

---

**Thank you for contributing to NeuroLens! Together, we're making neurodiversity a superpower! 🌈**
