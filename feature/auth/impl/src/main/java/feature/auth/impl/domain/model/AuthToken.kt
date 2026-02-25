package feature.auth.impl.domain.model

data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
)