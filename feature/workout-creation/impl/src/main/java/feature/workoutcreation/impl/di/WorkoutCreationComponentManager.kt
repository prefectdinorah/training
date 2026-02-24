package feature.workoutcreation.impl.di

import core.di.AppApi
import core.di.ComponentStorage
import core.di.get
import support.navigation.NavigationApi

object WorkoutCreationComponentManager {

    fun register() = with(ComponentStorage) {
        registerWithParent<AppApi, WorkoutCreationComponent> {
            DaggerWorkoutCreationComponent.builder()
                .appApi(get())
                .navigationApi(get())
                .build()
        }
    }
}