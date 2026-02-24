package feature.workoutcreation.impl.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface Screen : NavKey {
    @Serializable data object StepOne : Screen
    @Serializable data object StepTwo : Screen
    @Serializable data object Summary : Screen
}