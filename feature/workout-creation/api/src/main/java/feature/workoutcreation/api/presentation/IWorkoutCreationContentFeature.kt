package feature.workoutcreation.api.presentation

import androidx.compose.runtime.Composable

interface IWorkoutCreationContentFeature {

    @Composable
    fun Content(onFinish: () -> Unit, onBack: () -> Unit)
}