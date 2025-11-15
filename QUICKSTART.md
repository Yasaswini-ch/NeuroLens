# NeuroLens Quick Start Guide

## 🚀 Get Up and Running in 5 Minutes

### Prerequisites

Before you begin, ensure you have:

- ✅ **Android Studio** (Arctic Fox or later)
- ✅ **JDK 11** or higher
- ✅ **Android SDK 24+** (Android 7.0)
- ✅ **Git** installed

### Step 1: Clone the Repository

```bash
git clone https://github.com/yourusername/neurolens.git
cd neurolens
```

### Step 2: Open in Android Studio

1. Launch Android Studio
2. Select **File → Open**
3. Navigate to the `neurolens` directory
4. Click **OK**

### Step 3: Sync Gradle

Android Studio will automatically prompt you to sync Gradle. If not:

1. Click **File → Sync Project with Gradle Files**
2. Wait for dependencies to download (first time may take 2-5 minutes)

### Step 4: Run the App

#### Option A: Using Android Emulator

1. Click **Tools → Device Manager**
2. Create a new virtual device (Pixel 5 recommended)
3. Click the **Run** button (green triangle) in the toolbar
4. Select your emulator from the list

#### Option B: Using Physical Device

1. Enable **Developer Options** on your Android device:
    - Go to **Settings → About Phone**
    - Tap **Build Number** 7 times
    - Return to **Settings → System → Developer Options**
    - Enable **USB Debugging**

2. Connect your device via USB
3. Click the **Run** button
4. Select your device from the list

### Step 5: Explore the App

Once the app launches, you'll see:

1. **Home Screen** - Dashboard with all 6 modules
2. **Theme Switcher** - Settings icon (top right) → Try all 3 themes!
3. **Modules** - Click any module to explore:
    - **Emotion Mirror** - Full UI ready
    - **Prompt Rewriter** - Working text transformation
    - **Other Modules** - Coming soon screens

## 🎨 Try the Themes

### Switch Between Themes

1. Tap the **Settings** icon (top right)
2. Choose your theme:
    - **Zen Garden** - Calm, nature-inspired (default)
    - **Cyber Clarity** - High contrast, neon
    - **Comic Mode** - Bright and energizing
3. Notice how the entire app changes!

### Theme Characteristics

**Zen Garden 🌿**

- Colors: Earthy and warm, inspired by terracotta and sand tones.
- Best for: Daily use, calming focus
- Accessibility: High contrast, easy on eyes

**Cyber Clarity 🌐**

- Colors: Neon cyan, purple, pink
- Best for: Night mode, high-energy work
- Accessibility: Maximum contrast, dark mode

**Comic Mode 🎨**

- Colors: Bright red, turquoise, yellow
- Best for: Motivation, uplifting mood
- Accessibility: High saturation, playful

## 📱 Test the Features

### Emotion Mirror

1. From Home, tap **Emotion Mirror**
2. See the breathing circle animation
3. Observe the beautiful UI design
4. *Note: Camera integration coming soon*

### Prompt Rewriter

1. From Home, tap **Smart Prompt Rewriter**
2. Type a message: "hey can you maybe help me?"
3. Tap **Rewrite All**
4. See 6 different tone variations!
5. Try each tone chip for individual rewrites

## 🛠️ Project Structure at a Glance

```
neurolens/
├── app/src/main/java/com/example/neurolens/
│   ├── MainActivity.kt              ← Entry point
│   ├── data/models/                 ← Data classes
│   ├── ml/                          ← ML processors
│   └── ui/
│       ├── theme/                   ← Theme system
│       ├── components/              ← Reusable UI
│       ├── home/                    ← Home screen
│       ├── emotionmirror/           ← Module 1
│       └── promptrewriter/          ← Module 2
│
└── app/src/main/res/
    ├── values/strings.xml           ← All text
    ├── values/colors.xml            ← Color palette
    └── values/themes.xml            ← XML themes
```

## 🐛 Common Issues & Solutions

### Issue: Gradle Sync Failed

**Solution:**

```bash
# Clean and rebuild
./gradlew clean
./gradlew build
```

### Issue: App Won't Run on Emulator

**Solution:**

- Ensure emulator has API Level 24 or higher
- Try creating a new emulator (Pixel 5 recommended)
- Check if virtualization is enabled in BIOS

### Issue: Missing Dependencies

**Solution:**

1. Open `build.gradle.kts`
2. Click "Sync Now" notification
3. If still failing: **File → Invalidate Caches → Restart**

### Issue: Compose Preview Not Working

**Solution:**

- Ensure Android Studio is up to date
- Enable Compose Preview: **File → Settings → Editor → Compose Preview**
- Rebuild project

## 🧪 Running Tests

### Unit Tests

```bash
./gradlew test
```

### UI Tests (Instrumented)

```bash
./gradlew connectedAndroidTest
```

### Lint Checks

```bash
./gradlew lint
```

## 📚 Next Steps

### For Exploration

1. **Browse the Code**
    - Start with `MainActivity.kt`
    - Explore `HomeScreen.kt` for UI patterns
    - Check out `EmotionMirrorViewModel.kt` for state management

2. **Modify a Screen**
    - Open `PromptRewriterScreen.kt`
    - Change a color or text
    - See hot reload in action!

3. **Create a New Module**
    - Copy `emotionmirror` package structure
    - Add navigation in `MainActivity.kt`
    - Add module card in `HomeScreen.kt`

### For Development

1. **Read the Docs**
    - [`README.md`](README.md) - Project overview
    - [`ARCHITECTURE.md`](ARCHITECTURE.md) - Technical details
    - [`PROJECT_SUMMARY.md`](PROJECT_SUMMARY.md) - What's built

2. **Check Issues**
    - Look for `good first issue` labels
    - Read CONTRIBUTING.md
    - Join community discussions

3. **Set Up Your Environment**
    - Install Android Studio plugins:
        - Kotlin Multiplatform Mobile
        - Jetpack Compose Preview
    - Configure code style (Kotlin official)

## 🎯 Key Commands

### Build

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease
```

### Install

```bash
# Install on connected device
./gradlew installDebug
```

### Clean

```bash
# Clean build artifacts
./gradlew clean
```

### Check

```bash
# Run all checks
./gradlew check
```

## 💡 Pro Tips

### Hot Reload

- Compose supports hot reload!
- Make UI changes and see them instantly
- No need to restart the app

### Live Edit

1. While app is running, edit a `@Composable` function
2. Click **Apply Changes** (Ctrl+F10 / Cmd+F10)
3. See changes immediately!

### Preview

Add `@Preview` to any Composable:

```kotlin
@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    NeuroLensTheme {
        MyScreen()
    }
}
```

View it in the **Compose Preview** pane!

### Debugging

- Set breakpoints in ViewModels
- Use **Logcat** to see print statements
- **Database Inspector** for data (when we add it)

## 📞 Getting Help

### Documentation

- [README.md](README.md) - Full project documentation
- [ARCHITECTURE.md](ARCHITECTURE.md) - Technical architecture
- [Android Developer Docs](https://developer.android.com)

### Community

- **GitHub Issues** - Report bugs or request features
- **Discussions** - Ask questions, share ideas
- **Discord** (coming soon) - Real-time community support

### Resources

- [Jetpack Compose Tutorial](https://developer.android.com/jetpack/compose/tutorial)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- [Material 3 Design](https://m3.material.io)

## ✅ Checklist

Before you start developing, ensure:

- [ ] App builds successfully
- [ ] App runs on emulator or device
- [ ] You can switch themes
- [ ] Prompt Rewriter works
- [ ] You've explored all screens
- [ ] You've read README.md
- [ ] You understand the project structure

## 🎉 You're Ready!

Congratulations! You now have NeuroLens running locally. Here's what to do next:

1. **Explore** the existing code
2. **Experiment** with the UI
3. **Read** the architecture docs
4. **Contribute** your ideas
5. **Build** something amazing!

Remember: **NeuroLens is about empowering neurodiversity through technology.** Every contribution
makes a difference! 🌈

---

**Happy Coding!**

*Last Updated: January 2025*
