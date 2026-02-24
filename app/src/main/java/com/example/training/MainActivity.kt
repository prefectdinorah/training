package com.example.training

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.training.auth.AuthNavHost
import com.example.training.navigation.NavigationItem.DashboardItem
import com.example.training.navigation.NavigationItem.ProfileItem
import com.example.training.navigation.NavigationItem.SettingsItem
import com.example.training.navigation.NavigationItem.WorkoutItem
import core.viewmodel.getComponent
import feature.workoutcreation.api.di.WorkoutCreationApi
import feature.workoutcreation.api.presentation.launcher.WorkoutCreationEntryKey
import fitness.theme.PulseFitColors
import fitness.theme.TrainingTheme
import kotlinx.serialization.Serializable
import support.navigation.FeatureNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            TrainingTheme {
//                val mainRouter = rememberMainRouter(startTab = DashboardItem)
//
//                val dashboardNavigator = rememberFeatureNavigator<NavKey>(startKey = DashboardItem)
//                val workoutNavigator = rememberFeatureNavigator<NavKey>(startKey = WorkoutItem)
//                val profileNavigator = rememberFeatureNavigator<NavKey>(startKey = ProfileItem)
//                val settingsNavigator = rememberFeatureNavigator<NavKey>(startKey = SettingsItem)
//
//                BackHandler(enabled = mainRouter.canGoBack) {
//                    mainRouter.back()
//                }
//
//                val currentNavigator = when (mainRouter.currentTab) {
//                    DashboardItem -> dashboardNavigator
//                    WorkoutItem -> workoutNavigator
//                    ProfileItem -> profileNavigator
//                    else -> settingsNavigator
//                }
//                val showBottomBar = currentNavigator.backStack.size == 1
//
//                Scaffold(
//                    bottomBar = { if (showBottomBar) BottomNavigationBar(router = mainRouter) },
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(color = MaterialTheme.colorScheme.background)
//                ) { paddingValues ->
//                    val modifier = Modifier
//                        .fillMaxSize()
//                        .padding(paddingValues)
//
//                    when (mainRouter.currentTab) {
//                        DashboardItem -> DashboardFeature(modifier, dashboardNavigator)
//                        WorkoutItem -> WorkoutFeature(modifier, workoutNavigator)
//                        ProfileItem -> ProfileFeature(modifier, profileNavigator)
//                        SettingsItem -> SettingsFeature(modifier, settingsNavigator)
//                    }
//                }
//            }
            TrainingTheme {
                AuthNavHost(
                    onAuthComplete = {

                    }
                )
            }
        }
    }
}

@Serializable
data class DashboardDetailKey(val id: String) : NavKey

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
                    onCreateWorkout = { navigator.navigate(WorkoutCreationEntryKey) },
                )
            }
            entry<DashboardDetailKey> { key ->
                DashboardDetailScreen(id = key.id, onBack = { navigator.back() })
            }
            entry<WorkoutCreationEntryKey> {
                getComponent<WorkoutCreationApi>().workoutCreationFeature().Content(
                    onFinish = { navigator.back() },
                    onBack = { navigator.back() },
                )
            }
        },
    )
}

@Composable
private fun WorkoutFeature(modifier: Modifier, navigator: FeatureNavigator<NavKey>) {
    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        onBack = { navigator.back() },
        entryProvider = entryProvider {
            entry<WorkoutItem> { PlaceholderScreen("Workout") }
        },
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
        },
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
        },
    )
}

@Composable
private fun DashboardScreen(
    onOpenDetail: (id: String) -> Unit,
    onCreateWorkout: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Dashboard")
        Button(onClick = { onOpenDetail("42") }) { Text("Открыть деталку") }
        Button(onClick = onCreateWorkout) { Text("Создать тренировку") }
    }
}

@Composable
private fun DashboardDetailScreen(id: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Detail — id: $id")
        Button(onClick = onBack) { Text("Назад") }
    }
}

@Composable
private fun PlaceholderScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(name)
    }
}