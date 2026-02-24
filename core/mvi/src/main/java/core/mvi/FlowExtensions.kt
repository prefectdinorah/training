package core.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

fun <T> MutableSharedFlow<T>.resetReplayAfterCollect(): Flow<T> = flow {
    collect { value ->
        resetReplayCache()
        emit(value)
    }
}