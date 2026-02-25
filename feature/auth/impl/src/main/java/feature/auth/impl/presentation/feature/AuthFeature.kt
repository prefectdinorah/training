package feature.auth.impl.presentation.feature

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import feature.auth.impl.presentation.screen.login.composable.LoginScreen
import feature.auth.impl.presentation.screen.registration.composable.RegisterScreen
import feature.auth.impl.presentation.screen.WelcomeScreen

internal enum class AuthRoute { Welcome, Login, Register }

@Composable
internal fun AuthFeature(
    onAuthComplete: () -> Unit = {},
) {
    var currentRoute by remember { mutableStateOf(AuthRoute.Welcome) }

    AnimatedContent(
        targetState = currentRoute,
        transitionSpec = {
            fadeIn(tween(300)) + slideInHorizontally(
                initialOffsetX = { if (targetState.ordinal > initialState.ordinal) it / 3 else -it / 3 },
                animationSpec = tween(300),
            ) togetherWith fadeOut(tween(200))
        },
        label = "authNav",
    ) { route ->
        when (route) {
            AuthRoute.Welcome -> WelcomeScreen(
                onNavigateToLogin = { currentRoute = AuthRoute.Login },
                onNavigateToRegister = { currentRoute = AuthRoute.Register },
            )

            AuthRoute.Login -> LoginScreen(
                onBack = { currentRoute = AuthRoute.Welcome },
                onNavigateToRegister = { currentRoute = AuthRoute.Register },
                onLogin = onAuthComplete,
            )

            AuthRoute.Register -> RegisterScreen(
                onBack = { currentRoute = AuthRoute.Welcome },
                onNavigateToLogin = { currentRoute = AuthRoute.Login },
                onRegister = onAuthComplete,
            )
        }
    }
}