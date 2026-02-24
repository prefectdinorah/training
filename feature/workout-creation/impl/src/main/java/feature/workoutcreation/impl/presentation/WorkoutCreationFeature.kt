package feature.workoutcreation.impl.presentation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import feature.workoutcreation.impl.presentation.navigation.Screen
import feature.workoutcreation.impl.presentation.screen.WorkoutCreationStepOneScreen
import feature.workoutcreation.impl.presentation.screen.WorkoutCreationStepTwoScreen
import feature.workoutcreation.impl.presentation.screen.WorkoutCreationSummaryScreen
import support.navigation.rememberFeatureNavigator

@Composable
internal fun WorkoutCreationFeature(
    onFinish: () -> Unit,
    onBack: () -> Unit,
) {
    val navigator = rememberFeatureNavigator<Screen>(startKey = Screen.StepOne)

    NavDisplay(
        backStack = navigator.backStack,
        onBack = { if (!navigator.back()) onBack() },
        entryProvider = entryProvider {
            entry<Screen.StepOne> {
                WorkoutCreationStepOneScreen(
                    onNext = { navigator.navigate(Screen.StepTwo) },
                    onBack = onBack,
                )
            }
            entry<Screen.StepTwo> {
                WorkoutCreationStepTwoScreen(
                    onNext = { navigator.navigate(Screen.Summary) },
                    onBack = { navigator.back() },
                )
            }
            entry<Screen.Summary> {
                WorkoutCreationSummaryScreen(
                    onFinish = onFinish,
                    onBack = { navigator.back() },
                )
            }
        },
    )
}