package feature.main.api.di

import feature.main.api.presentation.IMainContentFeature

interface MainApi {
    fun mainFeature(): IMainContentFeature
}