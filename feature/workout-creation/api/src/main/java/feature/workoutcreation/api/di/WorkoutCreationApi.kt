package feature.workoutcreation.api.di

import feature.workoutcreation.api.presentation.IWorkoutCreationContentFeature
import feature.workoutcreation.api.presentation.launcher.IWorkoutCreationLauncher
import support.navigation.NavigationHolder

interface WorkoutCreationApi {
    fun workoutCreationLauncher(): IWorkoutCreationLauncher
    fun workoutCreationFeature(): IWorkoutCreationContentFeature
    fun navigationHolder(): NavigationHolder
}