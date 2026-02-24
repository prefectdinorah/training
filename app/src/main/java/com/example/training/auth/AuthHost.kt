package com.example.training.auth

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*

enum class AuthRoute { Welcome, Login, Register }

@Composable
fun AuthNavHost(
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
                onLogin = { email, password ->
                    onAuthComplete()
                },
            )

            AuthRoute.Register -> RegisterScreen(
                onBack = { currentRoute = AuthRoute.Welcome },
                onNavigateToLogin = { currentRoute = AuthRoute.Login },
                onRegister = { name, email, password ->
                    // TODO: registration logic
                    onAuthComplete()
                },
            )
        }
    }
}