package feature.auth.impl.data.network

import feature.auth.impl.data.dto.request.LoginRequestDto
import feature.auth.impl.data.dto.response.LoginResponseDto
import feature.auth.impl.data.dto.request.RegistrationRequestDto
import feature.auth.impl.data.dto.response.RegistrationResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class AuthApiService(private val client: HttpClient) {

    suspend fun login(request: LoginRequestDto): LoginResponseDto =
        client.post("auth/login") {
            setBody(request)
        }.body()

    suspend fun register(request: RegistrationRequestDto): RegistrationResponseDto =
        client.post("auth/register") {
            setBody(request)
        }.body()
}