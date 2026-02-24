package core.mvi

import android.util.Log

object Logger {
    fun error(throwable: Throwable) {
        Log.e("MVI", throwable.message ?: "Unknown error", throwable)
    }
}