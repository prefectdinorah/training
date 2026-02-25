package feature.auth.impl.domain.repository

import feature.auth.impl.domain.model.AuthToken

internal interface AuthRepository {

    suspend fun login(email: String, password: String): Result<AuthToken>

    suspend fun register(name: String, email: String, password: String): Result<AuthToken>

    suspend fun logout()
}