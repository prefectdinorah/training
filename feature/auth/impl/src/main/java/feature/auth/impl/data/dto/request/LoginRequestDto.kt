package feature.auth.impl.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LoginRequestDto(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)