package core.di

import android.app.Application
import android.content.Context

interface AppApi {
    fun application(): Application
    fun context(): Context
}