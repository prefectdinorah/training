package feature.main.impl.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import core.viewmodel.getComponent
import feature.main.impl.presentation.components.BottomNavigationBar
import feature.main.impl.navigation.NavigationItem.FoodItem
import feature.main.impl.navigation.NavigationItem.SettingsItem
import feature.main.impl.navigation.NavigationItem.TrainersItem
import feature.main.impl.navigation.NavigationItem.WorkoutItem
import feature.workoutcreation.api.di.WorkoutCreationApi
import feature.workoutcreation.api.presentation.launcher.WorkoutCreationEntryKey
import kotlinx.serialization.Serializable
import support.navigation.FeatureNavigator
import support.navigation.rememberFeatureNavigator
import support.navigation.rememberMainRouter

@Composable
internal fun MainScreen() {
    val mainRouter = rememberMainRouter(startTab = WorkoutItem)

    val workoutNavigator = rememberFeatureNavigator<NavKey>(startKey = WorkoutItem)
    val foodNavigator = rememberFeatureNavigator<NavKey>(startKey = FoodItem)
    val trainersNavigator = rememberFeatureNavigator<NavKey>(startKey = TrainersItem)
    val settingsNavigator = rememberFeatureNavigator<NavKey>(startKey = SettingsItem)

    BackHandler(enabled = mainRouter.canGoBack) {
        mainRouter.back()
    }

    val currentNavigator = when (mainRouter.currentTab) {
        WorkoutItem -> workoutNavigator
        FoodItem -> foodNavigator
        TrainersItem -> trainersNavigator
        else -> settingsNavigator
    }
    val showBottomBar = currentNavigator.backStack.size == 1

    Scaffold(
        bottomBar = { if (showBottomBar) BottomNavigationBar(router = mainRouter) },
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) { paddingValues ->
        val modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)

        when (mainRouter.currentTab) {
            WorkoutItem -> WorkoutFeature(modifier, workoutNavigator)
            FoodItem -> FoodFeature(modifier, foodNavigator)
            TrainersItem -> TrainersFeature(modifier, trainersNavigator)
            SettingsItem -> SettingsFeature(modifier, settingsNavigator)
        }
    }
}

@Serializable
internal data class WorkoutDetailKey(val id: String) : NavKey

@Composable
private fun WorkoutFeature(modifier: Modifier, navigator: FeatureNavigator<NavKey>) {
    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        onBack = { navigator.back() },
        entryProvider = entryProvider {
            entry<WorkoutItem> {
                WorkoutScreen(
                    onOpenDetail = { id -> navigator.navigate(WorkoutDetailKey(id)) },
                    onCreateWorkout = { navigator.navigate(WorkoutCreationEntryKey) },
                )
            }
            entry<WorkoutDetailKey> { key ->
                WorkoutDetailScreen(id = key.id, onBack = { navigator.back() })
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
private fun FoodFeature(modifier: Modifier, navigator: FeatureNavigator<NavKey>) {
    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        onBack = { navigator.back() },
        entryProvider = entryProvider {
            entry<FoodItem> { PlaceholderScreen("Food") }
        },
    )
}

@Composable
private fun TrainersFeature(modifier: Modifier, navigator: FeatureNavigator<NavKey>) {
    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        onBack = { navigator.back() },
        entryProvider = entryProvider {
            entry<TrainersItem> { PlaceholderScreen("Trainers") }
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
private fun WorkoutScreen(
    onOpenDetail: (id: String) -> Unit,
    onCreateWorkout: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Workout")
        Button(onClick = { onOpenDetail("42") }) { Text("Открыть деталку") }
        Button(onClick = onCreateWorkout) { Text("Создать тренировку") }
    }
}

@Composable
private fun WorkoutDetailScreen(id: String, onBack: () -> Unit) {
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