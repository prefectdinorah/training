package feature.workoutcreation.impl.di

import core.di.AppApi
import dagger.Binds
import dagger.Component
import dagger.Module
import feature.workoutcreation.api.di.WorkoutCreationApi
import feature.workoutcreation.api.presentation.IWorkoutCreationContentFeature
import feature.workoutcreation.api.presentation.launcher.IWorkoutCreationLauncher
import feature.workoutcreation.impl.presentation.WorkoutCreationContentFeature
import feature.workoutcreation.impl.presentation.launcher.WorkoutCreationLauncher
import support.navigation.NavigationApi
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class WorkoutCreationScope

@WorkoutCreationScope
@Component(
    modules = [WorkoutCreationBindModule::class],
    dependencies = [AppApi::class, NavigationApi::class],
)
internal interface WorkoutCreationComponent : WorkoutCreationApi

@Module
internal interface WorkoutCreationBindModule {

    @Binds
    fun bindLauncher(impl: WorkoutCreationLauncher): IWorkoutCreationLauncher

    @Binds
    fun bindFeature(impl: WorkoutCreationContentFeature): IWorkoutCreationContentFeature
}