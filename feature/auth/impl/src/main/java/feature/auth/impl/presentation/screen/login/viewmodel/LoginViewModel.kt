package feature.auth.impl.presentation.screen.login.viewmodel

import core.coroutine.CoroutineExceptionHandler
import core.coroutine.launchIO
import core.mvi.SimpleMviViewModel
import feature.auth.impl.domain.repository.AuthRepository
import feature.auth.impl.presentation.screen.login.model.LoginEffect
import feature.auth.impl.presentation.screen.login.model.LoginIntent
import feature.auth.impl.presentation.screen.login.model.LoginState
import javax.inject.Inject

internal class LoginViewModel @Inject constructor(
//    private val authRepository: AuthRepository,
) : SimpleMviViewModel<LoginIntent, LoginState, LoginEffect>() {

    override fun setInitialState() = LoginState()

    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> setState { copy(email = intent.value) }
            is LoginIntent.PasswordChanged -> setState { copy(password = intent.value) }
            is LoginIntent.TogglePasswordVisibility -> setState { copy(passwordVisible = !passwordVisible) }
            is LoginIntent.Submit -> handleSubmit()
        }
    }

    private fun handleSubmit() {
//        val state = viewState.value
//        launchIO(
//            errorHandler = CoroutineExceptionHandler { throwable ->
//                setState { copy(isLoading = false) }
//                setEffect { LoginEffect.Error(throwable.localizedMessage ?: "Ошибка входа") }
//            }
//        ) {
//            setState { copy(isLoading = true) }
//            authRepository.login(state.email, state.password)
//                .onSuccess { setEffect { LoginEffect.Success } }
//                .onFailure {
//                    setEffect {
//                        LoginEffect.Error(
//                            it.localizedMessage ?: "Ошибка входа"
//                        )
//                    }
//                }
//            setState { copy(isLoading = false) }
//        }
        // TODO: убрать когда бэкенд будет готов
        setEffect { LoginEffect.Success }
    }
}