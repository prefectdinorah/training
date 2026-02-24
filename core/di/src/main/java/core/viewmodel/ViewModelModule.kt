package core.viewmodel

import androidx.lifecycle.ViewModelProvider
import core.data.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

}