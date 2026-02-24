package com.example.training.di

import core.di.AppApi
import dagger.Component
import support.navigation.NavigationApi
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AppApi, NavigationApi