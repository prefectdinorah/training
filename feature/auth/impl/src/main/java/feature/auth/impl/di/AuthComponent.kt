package feature.auth.impl.di

import core.di.AppApi
import core.network.NetworkApi
import core.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import feature.auth.api.di.AuthApi
import feature.auth.api.presentation.feature.IAuthContentFeature
import feature.auth.api.presentation.launcher.IAuthLauncher
import feature.auth.impl.di.component.login.LoginScreenComponent
import feature.auth.impl.di.component.registration.RegistrationScreenComponent
import feature.auth.api.domain.session.IAuthSession
import feature.auth.impl.data.network.AuthApiService
import feature.auth.impl.data.repository.AuthRepositoryImpl
import feature.auth.impl.domain.repository.AuthRepository
import feature.auth.impl.presentation.feature.AuthContentFeature
import feature.auth.impl.presentation.launcher.AuthLauncher
import feature.auth.impl.domain.session.AuthSession
import io.ktor.client.HttpClient
import support.navigation.NavigationApi
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class AuthScope

@AuthScope
@Component(
    modules = [
        AuthProvideModule::class,
        AuthBindModule::class,
        ViewModelModule::class
    ],
    dependencies = [
        AppApi::class,
        NavigationApi::class,
        NetworkApi::class,
    ],
)
internal interface AuthComponent : AuthApi {

    fun plusLoginScreenComponent(): LoginScreenComponent

    fun plusRegistrationScreenComponent(): RegistrationScreenComponent
}

@Module
internal class AuthProvideModule {

    @Provides
    @AuthScope
    fun provideAuthApiService(client: HttpClient): AuthApiService = AuthApiService(client)
}

@Module
internal interface AuthBindModule {

    @Binds
    fun bindLauncher(impl: AuthLauncher): IAuthLauncher

    @Binds
    fun bindFeature(impl: AuthContentFeature): IAuthContentFeature

    @Binds
    fun bindAuthSession(impl: AuthSession): IAuthSession

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
