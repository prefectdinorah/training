package com.example.training

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import core.network.NetworkApi
import core.network.bus.AuthEvent
import core.viewmodel.getComponent
import feature.auth.api.di.AuthApi
import feature.main.api.di.MainApi
import fitness.theme.TrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val networkApi = getComponent<NetworkApi>()
            val authEventBus = networkApi.authEventBus()
            val tokenManager = networkApi.tokenManager()
            var isAuthenticated by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                authEventBus.events.collect { event ->
                    when (event) {
                        AuthEvent.ForceLogout -> {
                            tokenManager.clear()
                            isAuthenticated = false
                        }
                    }
                }
            }

            TrainingTheme {
                AnimatedContent(
                    targetState = isAuthenticated,
                    transitionSpec = {
                        if (targetState) {
                            fadeIn(tween(400)) + slideInHorizontally(
                                initialOffsetX = { it / 4 },
                                animationSpec = tween(400),
                            ) togetherWith fadeOut(tween(250))
                        } else {
                            fadeIn(tween(400)) + slideInHorizontally(
                                initialOffsetX = { -it / 4 },
                                animationSpec = tween(400),
                            ) togetherWith fadeOut(tween(250))
                        }
                    },
                    label = "authMainTransition",
                ) { authenticated ->
                    if (authenticated) {
                        getComponent<MainApi>().mainFeature().Content()
                    } else {
                        getComponent<AuthApi>().authFeature().Content(
                            onAuthComplete = { isAuthenticated = true }
                        )
                    }
                }
            }
        }
    }
}