package feature.auth.api.di

import feature.auth.api.presentation.feature.IAuthContentFeature
import feature.auth.api.presentation.launcher.IAuthLauncher
import feature.auth.api.domain.session.IAuthSession
import support.navigation.NavigationHolder

interface AuthApi {
    fun authLauncher(): IAuthLauncher
    fun authFeature(): IAuthContentFeature
    fun authSession(): IAuthSession
    fun navigationHolder(): NavigationHolder
}