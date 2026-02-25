package feature.auth.impl.di

import core.di.AppApi
import core.di.ComponentStorage
import core.di.get
import core.network.NetworkApi
import feature.auth.impl.di.component.login.LoginScreenComponent
import feature.auth.impl.di.component.registration.RegistrationScreenComponent

object AuthComponentManager {

    fun register() = with(ComponentStorage) {
        registerWithParent<AppApi, AuthComponent> {
            DaggerAuthComponent.builder()
                .appApi(get())
                .navigationApi(get())
                .networkApi(get())
                .build()
        }

        registerWithParent(provider = AuthComponent::plusLoginScreenComponent)
        registerWithParent(provider = AuthComponent::plusRegistrationScreenComponent)
    }
}