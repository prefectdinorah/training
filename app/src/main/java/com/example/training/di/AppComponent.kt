package com.example.training.di

import core.di.AppApi
import core.network.NetworkApi
import core.network.di.NetworkModule
import dagger.Component
import support.navigation.NavigationApi
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent : AppApi, NavigationApi, NetworkApi