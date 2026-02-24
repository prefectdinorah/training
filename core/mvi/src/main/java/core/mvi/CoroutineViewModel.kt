package core.mvi

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

abstract class CoroutineViewModel : BaseViewModel(), CoroutineScope {

    private val coroutineContextWithExceptionHandler by lazy {
        viewModelScope.coroutineContext + CoroutineExceptionHandler(::errorHandler)
    }

    override val coroutineContext: CoroutineContext = coroutineContextWithExceptionHandler

    open fun errorHandler(context: CoroutineContext, throwable: Throwable) {
        Logger.error(throwable)
    }

}