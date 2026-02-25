package core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import core.data.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.Multibinds

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

    @Multibinds
    fun viewModels(): @JvmSuppressWildcards Map<Class<out ViewModel>, ViewModel>

}