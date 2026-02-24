package feature.workoutcreation.impl.presentation.launcher

import feature.workoutcreation.api.presentation.launcher.IWorkoutCreationLauncher
import feature.workoutcreation.api.presentation.launcher.WorkoutCreationEntryKey
import support.navigation.NavigationHolder
import javax.inject.Inject

internal class WorkoutCreationLauncher @Inject constructor(
    private val navigationHolder: NavigationHolder,
) : IWorkoutCreationLauncher {

    override fun openWorkoutCreation() {
        navigationHolder.navigate(WorkoutCreationEntryKey)
    }
}