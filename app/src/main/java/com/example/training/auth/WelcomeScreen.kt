package com.example.training.auth

import fitness.theme.*
import fitness.component.utils.PreviewPhone
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import fitness.component.button.FitnessAccentButton
import fitness.component.button.FitnessGhostButton

@Composable
fun WelcomeScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
) {
    val accent = if (isSystemInDarkTheme()) PulseFitColors.Accent else PulseFitColors.AccentDark
    val background = MaterialTheme.colorScheme.background
    val textPrimary = MaterialTheme.colorScheme.onBackground
    val textSecondary = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
    val glowColor = accent.copy(alpha = .07f)

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulse1 by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.06f,
        animationSpec = infiniteRepeatable(
            tween(3000, easing = EaseInOutCubic), RepeatMode.Reverse
        ), label = "ring1"
    )
    val pulse2 by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.06f,
        animationSpec = infiniteRepeatable(
            tween(3000, delayMillis = 500, easing = EaseInOutCubic), RepeatMode.Reverse
        ), label = "ring2"
    )
    val pulse3 by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.06f,
        animationSpec = infiniteRepeatable(
            tween(3000, delayMillis = 1000, easing = EaseInOutCubic), RepeatMode.Reverse
        ), label = "ring3"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .drawBehind {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            glowColor,
                            Color.Transparent,
                        ),
                        center = Offset(size.width / 2, 0f),
                        radius = size.width * 0.7f,
                    ),
                    center = Offset(size.width / 2, 0f),
                    radius = size.width * 0.7f,
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.weight(1f))

            // ── Animated rings ──────────────────────────────────────────
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(180.dp),
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .scale(pulse1)
                        .clip(CircleShape)
                        .border(2.dp, accent.copy(alpha = .15f), CircleShape)
                )

                Box(
                    Modifier
                        .size(140.dp)
                        .scale(pulse2)
                        .clip(CircleShape)
                        .border(2.dp, accent.copy(alpha = .25f), CircleShape)
                )

                Box(
                    Modifier
                        .size(100.dp)
                        .scale(pulse3)
                        .clip(CircleShape)
                        .border(2.5.dp, accent.copy(alpha = .4f), CircleShape)
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                listOf(PulseFitColors.Accent, PulseFitColors.AccentDark)
                            )
                        ),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Bolt,
                        contentDescription = null,
                        tint = Color(0xFF111111),
                        modifier = Modifier.size(36.dp),
                    )
                }
            }

            Spacer(Modifier.height(36.dp))

            Text(
                buildAnnotatedString {
                    append("PULSE")
                    withStyle(SpanStyle(color = accent)) {
                        append("FIT")
                    }
                },
                style = PulseFitType.DisplayLarge.copy(color = textPrimary),
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Тренируйся умнее.\nОтслеживай прогресс.\nДостигай целей.",
                style = PulseFitType.BodyLarge.copy(color = textSecondary),
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 260.dp),
            )

            Spacer(Modifier.weight(1f))

            FitnessAccentButton(
                text = "Создать аккаунт",
                onClick = onNavigateToRegister,
            )

            Spacer(Modifier.height(12.dp))

            FitnessGhostButton(
                text = "Уже есть аккаунт",
                onClick = onNavigateToLogin,
            )

            Spacer(Modifier.height(50.dp))
        }
    }
}

@PreviewPhone
@Composable
private fun WelcomeScreenPreview() {
    PreviewAppTheme {
        WelcomeScreen(onNavigateToLogin = {}, onNavigateToRegister = {})
    }
}