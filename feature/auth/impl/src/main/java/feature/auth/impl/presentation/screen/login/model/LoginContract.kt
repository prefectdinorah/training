package feature.auth.impl.presentation.screen.login.model

sealed class LoginIntent {
    data class EmailChanged(val value: String) : LoginIntent()
    data class PasswordChanged(val value: String) : LoginIntent()
    object TogglePasswordVisibility : LoginIntent()
    object Submit : LoginIntent()
}

data class LoginState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
)

sealed class LoginEffect {
    object Success : LoginEffect()
    data class Error(val message: String) : LoginEffect()
}