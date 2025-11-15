# Git Setup Guide for NeuroLens

## ✅ Git Configuration Complete

Your Git is now configured with:

- **Username:** Yasaswini-ch
- **Email:** cheboluyasaswini@gmail.com
- **Line Endings:** Automatically handled (CRLF on Windows, LF in repository)

## 🚀 Quick Start - Push to GitHub

### Step 1: Commit Your Changes

```powershell
# Add all files
git add .

# Commit with a descriptive message
git commit -m "Initial commit: NeuroLens - AI-Powered Cognitive Companion

- Complete MVVM architecture with Jetpack Compose
- 6 core modules: Context Coach, Prompt Rewriter, Focus Bubble, Visual Reader, Emotion Mirror, Insight Journal
- 3 theme variants: Zen Garden, Cyber Clarity, Comic Mode
- On-device ML with ML Kit for privacy-first processing
- Full offline functionality
- Accessibility-first design for neurodiverse users
- Material 3 design system
- Kotlin 2.0.21 with modern best practices"
```

### Step 2: Create GitHub Repository

1. Go to https://github.com/new
2. Repository name: `NeuroLens` or `neurolens`
3. Description: "AI-Powered Cognitive Companion for Neurodiverse Users - Privacy-First,
   Offline-First Android App"
4. Choose **Public** or **Private**
5. **DO NOT** initialize with README (we already have one)
6. Click "Create repository"

### Step 3: Link and Push to GitHub

```powershell
# Add your GitHub repository as remote
git remote add origin https://github.com/Yasaswini-ch/NeuroLens.git

# Rename branch to main (if needed)
git branch -M main

# Push to GitHub
git push -u origin main
```

## 📝 Common Git Commands

### Daily Workflow

```powershell
# Check status
git status

# Add specific files
git add path/to/file.kt

# Add all changes
git add .

# Commit changes
git commit -m "Your commit message"

# Push to GitHub
git push

# Pull latest changes
git pull
```

### Branching

```powershell
# Create and switch to new branch
git checkout -b feature/new-feature

# Switch to existing branch
git checkout main

# List all branches
git branch -a

# Delete branch
git branch -d feature/old-feature
```

### Viewing Changes

```powershell
# View commit history
git log --oneline --graph --decorate

# View changes in working directory
git diff

# View changes in staged files
git diff --staged
```

### Undoing Changes

```powershell
# Discard changes in working directory
git checkout -- path/to/file.kt

# Unstage file
git reset HEAD path/to/file.kt

# Amend last commit
git commit --amend -m "Updated message"
```

## 🔧 Useful Git Configurations

```powershell
# Set default editor
git config --global core.editor "code --wait"

# Enable colored output
git config --global color.ui auto

# Set default branch name
git config --global init.defaultBranch main

# Cache credentials (Windows)
git config --global credential.helper wincred

# Set pull strategy
git config --global pull.rebase false
```

## 📦 .gitignore

Your project already has a comprehensive `.gitignore` file that excludes:

- Build outputs (`build/`, `*.apk`, `*.aab`)
- IDE files (`.idea/` specifics, `*.iml`)
- Local configuration (`local.properties`)
- Generated files

## 🎯 .gitattributes

Your project now has a `.gitattributes` file that:

- Automatically handles line endings
- Normalizes text files to LF in the repository
- Converts to CRLF on Windows checkout
- Treats binary files properly
- Ensures `gradlew` always uses LF

## 🌿 Recommended Branching Strategy

```
main                    (Production-ready code)
├── develop             (Integration branch)
│   ├── feature/context-coach
│   ├── feature/prompt-rewriter
│   └── feature/focus-bubble
└── hotfix/critical-bug
```

### Creating Feature Branches

```powershell
# From develop branch
git checkout develop
git pull

# Create feature branch
git checkout -b feature/new-module

# Work on feature...
git add .
git commit -m "Add new module"

# Push feature branch
git push -u origin feature/new-module

# When ready, create Pull Request on GitHub
```

## 🔐 GitHub Personal Access Token (for HTTPS)

If you're using HTTPS and need to authenticate:

1. Go to GitHub → Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Click "Generate new token (classic)"
3. Give it a name: "NeuroLens Development"
4. Select scopes: `repo` (full control of private repositories)
5. Click "Generate token"
6. **Copy the token** (you won't see it again!)
7. Use this token as your password when Git asks

Or use **GitHub CLI** for easier authentication:

```powershell
# Install GitHub CLI
winget install --id GitHub.cli

# Authenticate
gh auth login
```

## 📱 GitHub Mobile

Download the GitHub mobile app to:

- View commits and PRs on the go
- Review code
- Merge pull requests
- Get notifications

## 🎉 Next Steps After Pushing

1. **Add Topics** to your repo: `android`, `kotlin`, `jetpack-compose`, `machine-learning`,
   `accessibility`, `neurodiversity`, `privacy`, `offline-first`

2. **Create a Release:**
   ```
   Tag: v1.0.0
   Title: NeuroLens v1.0.0 - Initial Release
   Description: First public release of NeuroLens
   ```

3. **Add GitHub Actions** (optional) for CI/CD:
    - Automated builds
    - Code quality checks
    - Release automation

4. **Enable GitHub Pages** (optional) to host documentation

5. **Add Shields/Badges** to README:
   ```markdown
   ![Android](https://img.shields.io/badge/Android-7.0%2B-green)
   ![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-blue)
   ![License](https://img.shields.io/badge/License-MIT-yellow)
   ```

## 🐛 Troubleshooting

### Problem: "Authentication failed"

**Solution:** Use Personal Access Token instead of password, or use GitHub CLI

### Problem: "fatal: remote origin already exists"

**Solution:**

```powershell
git remote remove origin
git remote add origin https://github.com/Yasaswini-ch/NeuroLens.git
```

### Problem: "Updates were rejected because the remote contains work"

**Solution:**

```powershell
git pull origin main --rebase
git push origin main
```

### Problem: Large file error

**Solution:** Use Git LFS for files over 50MB

```powershell
git lfs install
git lfs track "*.apk"
git add .gitattributes
```

## 📚 Resources

- [Git Documentation](https://git-scm.com/doc)
- [GitHub Guides](https://guides.github.com/)
- [Atlassian Git Tutorials](https://www.atlassian.com/git/tutorials)
- [Oh Shit, Git!?!](https://ohshitgit.com/) - Get out of Git messes

---

**Happy Coding! 🚀**

*Created for NeuroLens by Yasaswini-ch*
