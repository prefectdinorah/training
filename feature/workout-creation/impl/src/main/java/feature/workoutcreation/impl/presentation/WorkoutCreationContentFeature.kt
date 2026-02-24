package feature.workoutcreation.impl.presentation

import androidx.compose.runtime.Composable
import feature.workoutcreation.api.presentation.IWorkoutCreationContentFeature
import javax.inject.Inject

internal class WorkoutCreationContentFeature @Inject constructor() : IWorkoutCreationContentFeature {

    @Composable
    override fun Content(onFinish: () -> Unit, onBack: () -> Unit) {
        WorkoutCreationFeature(onFinish = onFinish, onBack = onBack)
    }
}