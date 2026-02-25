package core.network

import core.network.bus.AuthEventBus
import core.network.token.TokenManager
import io.ktor.client.HttpClient

interface NetworkApi {
    fun httpClient(): HttpClient
    fun tokenManager(): TokenManager
    fun authEventBus(): AuthEventBus
}