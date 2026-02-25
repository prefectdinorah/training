package feature.auth.impl.presentation.screen.registration.viewmodel

import core.coroutine.CoroutineExceptionHandler
import core.coroutine.launchIO
import core.mvi.SimpleMviViewModel
import feature.auth.impl.domain.repository.AuthRepository
import feature.auth.impl.presentation.screen.registration.model.RegistrationEffect
import feature.auth.impl.presentation.screen.registration.model.RegistrationIntent
import feature.auth.impl.presentation.screen.registration.model.RegistrationState
import javax.inject.Inject

internal class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : SimpleMviViewModel<RegistrationIntent, RegistrationState, RegistrationEffect>() {

    override fun setInitialState() = RegistrationState()

    override fun handleIntent(intent: RegistrationIntent) {
        when (intent) {
            is RegistrationIntent.NameChanged -> setState { copy(name = intent.value) }
            is RegistrationIntent.EmailChanged -> setState { copy(email = intent.value) }
            is RegistrationIntent.PasswordChanged -> setState { copy(password = intent.value) }
            is RegistrationIntent.TogglePasswordVisibility -> setState { copy(passwordVisible = !passwordVisible) }
            is RegistrationIntent.Submit -> handleSubmit()
        }
    }

    private fun handleSubmit() {
        val state = viewState.value
        launchIO(
            errorHandler = CoroutineExceptionHandler { throwable ->
                setState { copy(isLoading = false) }
                setEffect { RegistrationEffect.Error(throwable.localizedMessage ?: "Ошибка регистрации") }
            }
        ) {
            setState { copy(isLoading = true) }
            authRepository.register(state.name, state.email, state.password)
                .onSuccess { setEffect { RegistrationEffect.Success } }
                .onFailure { setEffect { RegistrationEffect.Error(it.localizedMessage ?: "Ошибка регистрации") } }
            setState { copy(isLoading = false) }
        }
    }
}