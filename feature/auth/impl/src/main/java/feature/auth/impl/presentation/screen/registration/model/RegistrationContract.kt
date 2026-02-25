package feature.auth.impl.presentation.screen.registration.model

sealed class RegistrationIntent {
    data class NameChanged(val value: String) : RegistrationIntent()
    data class EmailChanged(val value: String) : RegistrationIntent()
    data class PasswordChanged(val value: String) : RegistrationIntent()
    object TogglePasswordVisibility : RegistrationIntent()
    object Submit : RegistrationIntent()
}

data class RegistrationState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
)

sealed class RegistrationEffect {
    object Success : RegistrationEffect()
    data class Error(val message: String) : RegistrationEffect()
}