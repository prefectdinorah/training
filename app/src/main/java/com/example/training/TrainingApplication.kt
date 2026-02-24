package com.example.training

import android.app.Application
import com.example.training.di.AppModule
import com.example.training.di.DaggerAppComponent
import core.di.ComponentStorage
import feature.workoutcreation.impl.di.WorkoutCreationComponentManager

class TrainingApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        ComponentStorage.register { appComponent }

        registerFeatureComponents()
    }

    private fun registerFeatureComponents() {
        WorkoutCreationComponentManager.register()
        // новые фичи добавляй здесь
    }
}