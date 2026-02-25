package feature.auth.impl.data.repository

import core.coroutine.runSuspendCatching
import core.coroutine.withContextIO
import core.network.token.TokenManager
import feature.auth.impl.data.dto.request.LoginRequestDto
import feature.auth.impl.data.dto.request.RegistrationRequestDto
import feature.auth.impl.data.network.AuthApiService
import feature.auth.impl.domain.model.AuthToken
import feature.auth.impl.domain.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val apiService: AuthApiService,
    private val tokenManager: TokenManager,
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<AuthToken> =
        withContextIO {
            runSuspendCatching {
                val response = apiService.login(LoginRequestDto(email, password))
                val token = AuthToken(
                    accessToken = response.accessToken,
                    refreshToken = response.refreshToken,
                )
                tokenManager.saveTokens(token.accessToken, token.refreshToken)
                token
            }
        }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
    ): Result<AuthToken> =
        withContextIO {
            runSuspendCatching {
                val response = apiService.register(RegistrationRequestDto(name, email, password))
                val token = AuthToken(
                    accessToken = response.accessToken,
                    refreshToken = response.refreshToken,
                )
                tokenManager.saveTokens(token.accessToken, token.refreshToken)
                token
            }
        }

    override suspend fun logout() = withContextIO {
        tokenManager.clear()
    }
}