# NeuroLens – An Offline Cognitive Companion for Neurodiverse Users

![Android](https://img.shields.io/badge/Android-7.0%2B-green)
![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Platform](https://img.shields.io/badge/Platform-Android-brightgreen)

**Tagline:** *Empowering Neurodiversity, One Interaction at a Time*

NeuroLens is a privacy-first, fully offline mobile app designed specifically for neurodiverse
users (ADHD, autism, dyslexia). Every feature runs entirely on-device using advanced ML
capabilities, ensuring absolute privacy, low latency, and high performance.

## 🌟 Core Philosophy

- **Privacy First:** All processing happens on your device. Zero cloud dependency.
- **Accessibility Always:** Designed with neurodiversity at its core.
- **Gentle Guidance:** Subtle, non-intrusive nudges and feedback.
- **Empowerment:** Making neurodiversity a superpower, not a challenge.

## ✨ Core Modules

### 1. Live Context Coach 🤝

Real-time social cue interpretation using on-device camera and microphone.

**Features:**

- Detects interruptions and conversation flow
- Analyzes loudness and speech pace
- Interprets facial reactions
- Provides subtle vibration, visual, or audio nudges

**Technology:**

- ML Kit Face Detection
- Audio processing for speech analysis
- Real-time pattern recognition

### 2. Smart Prompt Rewriter ✍️

Transform your messages into different tones with instant previews.

**Tone Options:**

- **Formal:** Professional and structured
- **Friendly:** Warm and approachable
- **Assertive:** Clear and confident
- **Simplified:** Easy to understand
- **Empathetic:** Understanding and supportive
- **Concise:** Brief and to the point

**Technology:**

- On-device text transformation
- Rule-based NLP (upgradeable to local LLMs)
- Real-time preview bubbles

### 3. Focus Bubble 🎯

Detect distraction patterns and maintain deep work states.

**Features:**

- Distraction detection via camera and motion sensors
- Animated "Focus Pulse" visualization
- Gentle visual nudges and timers
- Focus metrics and streaks
- Break reminders

**Technology:**

- Motion detection
- Focus state tracking
- Adaptive timing algorithms

### 4. Visual Overlay Reader 📖

On-device OCR with dyslexia-friendly formatting.

**Features:**

- Camera-based text extraction
- OpenDyslexic font support
- Adjustable spacing and backgrounds
- Quick text summarization
- Color-safe themes

**Technology:**

- ML Kit Text Recognition
- Dyslexia-optimized rendering
- Custom typography system

### 5. Emotion Mirror 😊

Understand your emotional state through facial analysis.

**Features:**

- Real-time emotion detection
- Confidence scoring
- Mood-based grounding suggestions
- Privacy-preserving processing
- Calming visualizations

**Technology:**

- ML Kit Face Detection with emotion classification
- Facial landmark analysis
- Breathing exercise animations

### 6. Insight Journal 📊

Track patterns, celebrate progress, and gain AI insights.

**Features:**

- Mood tracking and visualization
- Focus pattern analysis
- Communication metrics
- Streak gamification
- Personalized AI insights

**Technology:**

- Local data persistence
- Pattern recognition algorithms
- Calm, non-overstimulating charts

## 🎨 Design Philosophy

### Three Theme Variants

#### Zen Garden 🌿

- **Vibe:** Calm, nature-inspired
- **Colors:** Soft blues, greens, earth tones
- **Use Case:** Default calming experience

#### Cyber Clarity 🌐

- **Vibe:** High contrast, modern
- **Colors:** Neon accents on dark backgrounds
- **Use Case:** High-energy focus sessions

#### Comic Mode 🎨

- **Vibe:** Playful and energizing
- **Colors:** Bright, saturated colors
- **Use Case:** Motivational and uplifting

### UI Principles

- **Soft Edges:** Rounded corners everywhere (16-32dp)
- **High Contrast:** WCAG AAA compliance
- **Generous Spacing:** Reduced visual clutter
- **Micro-Interactions:** Delightful animations
- **Clear Hierarchy:** Easy navigation
- **Focus Pulse:** Signature breathing animation

## 🏗️ Architecture

### Tech Stack

```
- Language: Kotlin
- UI Framework: Jetpack Compose + Material 3
- ML: ML Kit (Face Detection, Text Recognition)
- Camera: CameraX
- Architecture: MVVM with clean architecture
- Async: Kotlin Coroutines + Flow
- Local Storage: DataStore
- Testing: JUnit, Espresso
```

### Project Structure

```
com.example.neurolens/
├── data/
│   └── models/          # Data classes (EmotionState, FocusSession, etc.)
├── ml/                  # On-device ML processors
│   ├── MLProcessor.kt
│   ├── EmotionDetector.kt
│   ├── TextRecognizer.kt
│   └── PromptRewriteEngine.kt
├── ui/
│   ├── theme/          # Theme, colors, typography
│   ├── components/     # Reusable UI components
│   ├── home/           # Main dashboard
│   ├── emotionmirror/  # Emotion Mirror module
│   ├── promptrewriter/ # Smart Prompt Rewriter
│   ├── focusbubble/    # Focus Bubble module
│   ├── visualreader/   # Visual Reader module
│   ├── contextcoach/   # Live Context Coach
│   └── journal/        # Insight Journal
└── MainActivity.kt
```

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 24+ (Android 7.0+)
- Kotlin 1.9+

### Installation

1. Clone the repository:

```bash
git clone https://github.com/yourusername/neurolens.git
cd neurolens
```

2. Open in Android Studio:

```bash
open -a "Android Studio" .
```

3. Sync Gradle and run:

```bash
./gradlew build
```

4. Run on device or emulator:

- Connect Android device or start emulator
- Click Run button in Android Studio

### Permissions

NeuroLens requires the following permissions:

- **Camera:** For emotion detection and OCR
- **Microphone:** For audio cue analysis
- **Vibration:** For haptic feedback

All permissions are requested at runtime with clear explanations.

## 🧪 Testing

```bash
# Unit tests
./gradlew test

# UI tests
./gradlew connectedAndroidTest

# Lint checks
./gradlew lint
```

## 📱 Screenshots

| Home Screen            | Emotion Mirror               | Focus Bubble             |
|------------------------|------------------------------|--------------------------|
| ![Home](docs/home.png) | ![Emotion](docs/emotion.png) | ![Focus](docs/focus.png) |

| Prompt Rewriter                | Visual Reader              | Insight Journal              |
|--------------------------------|----------------------------|------------------------------|
| ![Rewriter](docs/rewriter.png) | ![Reader](docs/reader.png) | ![Journal](docs/journal.png) |

## 🔒 Privacy & Security

- **Zero Cloud Dependency:** All ML processing happens on-device
- **No Data Collection:** We don't collect or transmit any user data
- **Local Storage Only:** All data stays on your device
- **Open Source:** Full transparency in our code

## 🛣️ Roadmap

### Phase 1 (Current)

- [x] Core architecture
- [x] Theme system (3 variants)
- [x] Emotion Mirror
- [x] Focus Bubble
- [ ] Prompt Rewriter UI
- [ ] Visual Reader UI
- [ ] Context Coach UI
- [ ] Insight Journal UI

### Phase 2 (Q2 2025)

- [ ] Integration with RunAnywhere SDK
- [ ] Local LLM for advanced rewriting
- [ ] Enhanced emotion recognition
- [ ] Voice input support
- [ ] Offline backup/restore

### Phase 3 (Q3 2025)

- [ ] Wearable integration
- [ ] Advanced analytics
- [ ] Custom theme builder
- [ ] Multi-language support

## 🤝 Contributing

We welcome contributions from the community! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for
guidelines.

### Areas We Need Help

- Accessibility testing with neurodiverse users
- Translation and localization
- UI/UX improvements
- Performance optimization
- Documentation

## 📄 License

This project is licensed under the MIT License - see [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Designed with input from neurodiverse community members
- Inspired by accessibility-first design principles
- Built with love for empowering neurodiversity

## 📞 Contact & Support

- **Email:** support@neurolens.app
- **Twitter:** [@NeuroLensApp](https://twitter.com/neurolensapp)
- **Discord:** [Join our community](https://discord.gg/neurolens)

## 🌈 Made with ❤️ for the Neurodiverse Community

---

*NeuroLens: Where Neurodiversity Becomes a Superpower*
