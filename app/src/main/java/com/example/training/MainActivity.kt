package com.example.training

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.training.components.BottomNavigationBar
import com.example.training.example.WorkoutCreationEntryKey
import com.example.training.example.WorkoutCreationFeature
import com.example.training.navigation.NavigationItem.DashboardItem
import com.example.training.navigation.NavigationItem.ProfileItem
import com.example.training.navigation.NavigationItem.SettingsItem
import com.example.training.navigation.NavigationItem.WorkoutItem
import fitness.theme.TrainingTheme
import kotlinx.serialization.Serializable
import support.navigation.FeatureNavigator
import support.navigation.rememberFeatureNavigator
import support.navigation.rememberMainRouter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrainingTheme {
                // Один MainRouter только для переключения табов
                val mainRouter = rememberMainRouter(startTab = DashboardItem)

                // Каждый таб получает свой FeatureNavigator — стек сохраняется при переключении
                val dashboardNavigator = rememberFeatureNavigator<NavKey>(startKey = DashboardItem)
                val workoutNavigator = rememberFeatureNavigator<NavKey>(startKey = WorkoutItem)
                val profileNavigator = rememberFeatureNavigator<NavKey>(startKey = ProfileItem)
                val settingsNavigator = rememberFeatureNavigator<NavKey>(startKey = SettingsItem)

                // Перехватываем «назад» только для истории табов.
                // Назад внутри фичи обрабатывает сам NavDisplay каждой фичи.
                BackHandler(enabled = mainRouter.canGoBack) {
                    mainRouter.back()
                }

                // Скрываем bottom bar когда фича ушла глубже стартового экрана
                val currentNavigator = when (mainRouter.currentTab) {
                    DashboardItem -> dashboardNavigator
                    WorkoutItem -> workoutNavigator
                    ProfileItem -> profileNavigator
                    else -> settingsNavigator
                }
                val showBottomBar = currentNavigator.backStack.size == 1

                Scaffold(
                    bottomBar = { if (showBottomBar) BottomNavigationBar(router = mainRouter) },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                ) { paddingValues ->
                    val modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)

                    // Показываем NavDisplay активного таба
                    when (mainRouter.currentTab) {
                        DashboardItem -> DashboardFeature(modifier, dashboardNavigator)
                        WorkoutItem -> WorkoutFeature(modifier, workoutNavigator)
                        ProfileItem -> ProfileFeature(modifier, profileNavigator)
                        SettingsItem -> SettingsFeature(modifier, settingsNavigator)
                    }
                }
            }
        }
    }
}

// ─── NavKey для деталки Dashboard ────────────────────────────────────────────
// Когда будет feature-модуль, этот класс переедет туда
@Serializable
data class DashboardDetailKey(val id: String) : NavKey

// ─── Пример структуры фичи ───────────────────────────────────────────────────
// Когда будут отдельные feature-модули, эти функции переедут туда
@Composable
private fun DashboardFeature(modifier: Modifier, navigator: FeatureNavigator<NavKey>) {
    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        onBack = { navigator.back() },
        entryProvider = entryProvider {
            entry<DashboardItem> {
                DashboardScreen(
                    onOpenDetail = { id -> navigator.navigate(DashboardDetailKey(id)) },
                    onCreateWorkout = { navigator.navigate(WorkoutCreationEntryKey) }
                )
            }
            entry<DashboardDetailKey> { key ->
                DashboardDetailScreen(
                    id = key.id,
                    onBack = { navigator.back() }
                )
            }
            // Точка входа в WorkoutCreation — фича управляет своим NavDisplay сама
            entry<WorkoutCreationEntryKey> {
                WorkoutCreationFeature(
                    onBack = { navigator.back() }
                )
            }
        }
    )
}

@Composable
private fun WorkoutFeature(modifier: Modifier, navigator: FeatureNavigator<NavKey>) {
    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        onBack = { navigator.back() },
        entryProvider = entryProvider {
            entry<WorkoutItem> {
                // Сюда придёт WorkoutListScreen(navigator)
                PlaceholderScreen("Workout")
            }
            // entry<WorkoutDetailKey> { key -> WorkoutDetailScreen(key, navigator) }
        }
    )
}

@Composable
private fun ProfileFeature(modifier: Modifier, navigator: FeatureNavigator<NavKey>) {
    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        onBack = { navigator.back() },
        entryProvider = entryProvider {
            entry<ProfileItem> { PlaceholderScreen("Profile") }
        }
    )
}

@Composable
private fun SettingsFeature(modifier: Modifier, navigator: FeatureNavigator<NavKey>) {
    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        onBack = { navigator.back() },
        entryProvider = entryProvider {
            entry<SettingsItem> { PlaceholderScreen("Settings") }
        }
    )
}

// ─── Dashboard screens ────────────────────────────────────────────────────────

@Composable
private fun DashboardScreen(
    onOpenDetail: (id: String) -> Unit,
    onCreateWorkout: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Text("Dashboard")
        Button(onClick = { onOpenDetail("42") }) {
            Text("Открыть деталку")
        }
        Button(onClick = onCreateWorkout) {
            Text("Создать тренировку (другая фича)")
        }
    }
}

@Composable
private fun DashboardDetailScreen(id: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Text("Dashboard Detail — id: $id")
        Button(onClick = onBack) {
            Text("Назад")
        }
    }
}

@Composable
private fun PlaceholderScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(name)
    }
}