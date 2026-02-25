package feature.auth.impl.di.component.registration

import androidx.lifecycle.ViewModel
import core.viewmodel.ViewModelApi
import core.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import feature.auth.impl.presentation.screen.registration.viewmodel.RegistrationViewModel
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class RegistrationScope

interface IRegistrationScreenComponent : ViewModelApi

@RegistrationScope
@Subcomponent(modules = [RegistrationViewModelModule::class])
internal interface RegistrationScreenComponent : IRegistrationScreenComponent

@Module
internal interface RegistrationViewModelModule {

    @Binds
    @IntoMap
    @RegistrationScope
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(impl: RegistrationViewModel): ViewModel
}