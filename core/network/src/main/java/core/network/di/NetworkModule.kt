package core.network.di

import core.network.BuildConfig
import core.network.bus.AuthEvent
import core.network.bus.AuthEventBus
import core.network.dto.RefreshRequest
import core.network.dto.TokenResponse
import core.network.token.TokenManager
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        tokenManager: TokenManager,
        authEventBus: AuthEventBus,
    ): HttpClient = HttpClient(OkHttp) {

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = true
            })
        }

        install(Auth) {
            bearer {
                loadTokens {
                    val access = tokenManager.getAccessToken() ?: ""
                    val refresh = tokenManager.getRefreshToken() ?: ""
                    BearerTokens(access, refresh)
                }

                refreshTokens {
                    val refresh = tokenManager.getRefreshToken()
                        ?: run {
                            authEventBus.emit(AuthEvent.ForceLogout)
                            return@refreshTokens null
                        }

                    try {
                        val response = client.post("${BuildConfig.BASE_URL}auth/refresh") {
                            contentType(ContentType.Application.Json)
                            setBody(RefreshRequest(refresh))
                            markAsRefreshTokenRequest()
                        }
                        val tokens = response.body<TokenResponse>()
                        tokenManager.saveTokens(tokens.accessToken, tokens.refreshToken)
                        BearerTokens(tokens.accessToken, tokens.refreshToken)
                    } catch (e: Exception) {
                        authEventBus.emit(AuthEvent.ForceLogout)
                        null
                    }
                }
            }
        }

        install(Logging) {
            logger = Logger.ANDROID
            level = if (BuildConfig.DEBUG) LogLevel.BODY else LogLevel.NONE
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 10_000
        }

        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 2)
            exponentialDelay()
        }

        defaultRequest {
            url(BuildConfig.BASE_URL)
            contentType(ContentType.Application.Json)
        }
    }
}