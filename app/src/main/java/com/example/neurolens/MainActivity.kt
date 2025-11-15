package com.example.neurolens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.neurolens.data.models.AppTheme
import com.example.neurolens.ui.contextcoach.ContextCoachScreen
import com.example.neurolens.ui.emotionmirror.EmotionMirrorScreen
import com.example.neurolens.ui.focusbubble.FocusBubbleScreen
import com.example.neurolens.ui.home.HomeScreen
import com.example.neurolens.ui.journal.InsightJournalScreen
import com.example.neurolens.ui.promptrewriter.PromptRewriterScreen
import com.example.neurolens.ui.theme.NeuroLensTheme
import com.example.neurolens.ui.visualreader.VisualReaderScreen

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentTheme by remember { mutableStateOf(AppTheme.ZEN_GARDEN) }

            NeuroLensTheme(theme = currentTheme) {
                NeuroLensApp(
                    currentTheme = currentTheme,
                    onThemeChange = { }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeuroLensApp(
    currentTheme: AppTheme,
    onThemeChange: (AppTheme) -> Unit
) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route ?: Screen.Home.route

    val currentScreen = when (currentRoute) {
        Screen.Home.route -> Screen.Home
        Screen.ContextCoach.route -> Screen.ContextCoach
        Screen.PromptRewriter.route -> Screen.PromptRewriter
        Screen.FocusBubble.route -> Screen.FocusBubble
        Screen.VisualReader.route -> Screen.VisualReader
        Screen.EmotionMirror.route -> Screen.EmotionMirror
        Screen.Journal.route -> Screen.Journal
        Screen.Settings.route -> Screen.Settings
        else -> Screen.Home
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(currentScreen.title) },
                navigationIcon = {
                    if (currentRoute != Screen.Home.route) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(Icons.Default.Settings, "Settings")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            NeuroLensNavHost(
                navController = navController,
                modifier = Modifier.fillMaxSize(),
                currentTheme = currentTheme,
                onThemeChange = onThemeChange
            )
        }
    }
}

@Composable
fun NeuroLensNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    currentTheme: AppTheme,
    onThemeChange: (AppTheme) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToContextCoach = {
                    navController.navigate(Screen.ContextCoach.route)
                },
                onNavigateToPromptRewriter = {
                    navController.navigate(Screen.PromptRewriter.route)
                },
                onNavigateToFocusBubble = {
                    navController.navigate(Screen.FocusBubble.route)
                },
                onNavigateToVisualReader = {
                    navController.navigate(Screen.VisualReader.route)
                },
                onNavigateToEmotionMirror = {
                    navController.navigate(Screen.EmotionMirror.route)
                },
                onNavigateToJournal = {
                    navController.navigate(Screen.Journal.route)
                }
            )
        }

        composable(Screen.ContextCoach.route) {
            ContextCoachScreen()
        }

        composable(Screen.PromptRewriter.route) {
            PromptRewriterScreen()
        }

        composable(Screen.FocusBubble.route) {
            FocusBubbleScreen()
        }

        composable(Screen.VisualReader.route) {
            VisualReaderScreen()
        }

        composable(Screen.EmotionMirror.route) {
            EmotionMirrorScreen()
        }

        composable(Screen.Journal.route) {
            InsightJournalScreen()
        }

        composable(Screen.Settings.route) {
            SettingsScreen(currentTheme = currentTheme, onThemeChange = onThemeChange)
        }
    }
}

@Composable
fun SettingsScreen(
    currentTheme: AppTheme,
    onThemeChange: (AppTheme) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Choose Your Theme",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        AppTheme.entries.forEach { theme ->
            val isSelected = currentTheme == theme
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (isSelected) Modifier.border(
                            BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(16.dp)
                        )
                        else Modifier
                    ),
                onClick = { onThemeChange(theme) },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (isSelected) 8.dp else 2.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    }
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = theme.displayName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = theme.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Selected",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}
