package feature.auth.impl.domain.session

import feature.auth.api.domain.session.IAuthSession
import feature.auth.impl.domain.repository.AuthRepository
import javax.inject.Inject

internal class AuthSession @Inject constructor(
    private val authRepository: AuthRepository,
) : IAuthSession {

    override suspend fun logout() = authRepository.logout()
}