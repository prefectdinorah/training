package feature.auth.api.domain.session

interface IAuthSession {
    suspend fun logout()
}