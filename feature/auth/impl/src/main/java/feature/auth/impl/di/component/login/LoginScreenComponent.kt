package feature.auth.impl.di.component.login

import androidx.lifecycle.ViewModel
import core.viewmodel.ViewModelApi
import core.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import feature.auth.impl.presentation.screen.login.viewmodel.LoginViewModel
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class LoginScope

interface ILoginScreenComponent : ViewModelApi

@LoginScope
@Subcomponent(modules = [LoginViewModelModule::class])
internal interface LoginScreenComponent : ILoginScreenComponent

@Module
internal interface LoginViewModelModule {

    @Binds
    @IntoMap
    @LoginScope
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(impl: LoginViewModel): ViewModel
}