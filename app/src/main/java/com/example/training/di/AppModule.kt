package com.example.training.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import support.navigation.NavigationHolder
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideNavigationHolder(): NavigationHolder = NavigationHolder()
}