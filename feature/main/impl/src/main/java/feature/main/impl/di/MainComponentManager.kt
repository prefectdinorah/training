package feature.main.impl.di

import core.di.AppApi
import core.di.ComponentStorage
import core.di.get

object MainComponentManager {

    fun register() = with(ComponentStorage) {
        registerWithParent<AppApi, MainComponent> {
            DaggerMainComponent.builder()
                .appApi(get())
                .build()
        }
    }
}