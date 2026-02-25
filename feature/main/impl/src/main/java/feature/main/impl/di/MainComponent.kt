package feature.main.impl.di

import core.di.AppApi
import dagger.Binds
import dagger.Component
import dagger.Module
import feature.main.api.di.MainApi
import feature.main.api.presentation.IMainContentFeature
import feature.main.impl.presentation.feature.MainContentFeature
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class MainScope

@MainScope
@Component(
    modules = [MainBindModule::class],
    dependencies = [AppApi::class],
)
internal interface MainComponent : MainApi

@Module
internal interface MainBindModule {

    @Binds
    fun bindFeature(impl: MainContentFeature): IMainContentFeature
}