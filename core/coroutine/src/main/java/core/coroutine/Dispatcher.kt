package core.coroutine

import kotlinx.coroutines.Dispatchers

object Dispatcher {
    val IO = Dispatchers.IO
    val Default = Dispatchers.Default
    val Unconfined = Dispatchers.Unconfined
}