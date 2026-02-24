package com.example.training.auth

import fitness.theme.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitness.component.button.FitnessAccentButton
import fitness.component.button.SocialButton
import fitness.component.divider.OrDivider
import fitness.component.text_field.FitnessTextField
import fitness.component.text_field.atom.PasswordStrengthIndicator
import fitness.component.utils.PreviewPhone

@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onRegister: (name: String, email: String, password: String) -> Unit = { _, _, _ -> },
) {
    val accent = if (isSystemInDarkTheme()) PulseFitColors.Accent else PulseFitColors.AccentDark
    val background = MaterialTheme.colorScheme.background
    val textPrimary = MaterialTheme.colorScheme.onBackground
    val textSecondary = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
    val textHint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
    val inputBg = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.06f)
    val inputBorder = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f)
    val glowColor = accent.copy(alpha = .07f)

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val isFormValid = name.isNotBlank() && email.isNotBlank() && password.isNotBlank()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .drawBehind {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(glowColor, Color.Transparent),
                        center = Offset(size.width / 2, 0f),
                        radius = size.width * 0.7f,
                    ),
                    center = Offset(size.width / 2, 0f),
                    radius = size.width * 0.7f,
                )
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(PulseFitColors.Teal.copy(alpha = .04f), Color.Transparent),
                        center = Offset(size.width + 60f, size.height + 100f),
                        radius = 400f,
                    ),
                    center = Offset(size.width + 60f, size.height + 100f),
                    radius = 400f,
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
        ) {
            Spacer(Modifier.height(60.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(inputBg)
                    .border(1.dp, inputBorder, RoundedCornerShape(12.dp))
                    .clickable(onClick = onBack),
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Назад",
                    tint = textPrimary,
                    modifier = Modifier.size(20.dp),
                )
            }

            Spacer(Modifier.height(28.dp))

            Text(
                text = "Создай аккаунт",
                style = PulseFitType.HeadlineMedium.copy(color = textPrimary),
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Начни свой путь к лучшей форме",
                style = PulseFitType.BodyLarge.copy(color = textSecondary),
            )

            Spacer(Modifier.height(32.dp))

            FitnessTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = "Имя",
                leadingIcon = Icons.Rounded.Person,
            )

            Spacer(Modifier.height(14.dp))

            FitnessTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email",
                leadingIcon = Icons.Rounded.Email,
            )

            Spacer(Modifier.height(14.dp))

            FitnessTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Пароль",
                leadingIcon = Icons.Rounded.Lock,
                isPassword = true,
                passwordVisible = passwordVisible,
                onTogglePasswordVisibility = { passwordVisible = !passwordVisible },
            )

            PasswordStrengthIndicator(password = password)

            Spacer(Modifier.height(24.dp))

            FitnessAccentButton(
                text = "Зарегистрироваться",
                enabled = isFormValid,
                onClick = { onRegister(name, email, password) },
            )

            Spacer(Modifier.height(24.dp))

            OrDivider()

            Spacer(Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SocialButton(label = "Google", icon = "", modifier = Modifier.weight(1f))
                SocialButton(label = "Apple",  icon = "",  modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = textHint)) {
                        append("Регистрируясь, вы принимаете ")
                    }
                    withStyle(SpanStyle(color = textSecondary)) {
                        append("Условия использования")
                    }
                    withStyle(SpanStyle(color = textHint)) {
                        append(" и ")
                    }
                    withStyle(SpanStyle(color = textSecondary)) {
                        append("Политику конфиденциальности")
                    }
                },
                style = PulseFitType.LabelSmall.copy(
                    letterSpacing = 0.sp,
                    lineHeight = 18.sp,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.weight(1f))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 44.dp),
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = textSecondary)) {
                            append("Уже есть аккаунт? ")
                        }
                        withStyle(
                            SpanStyle(
                                color = accent,
                                fontWeight = FontWeight.Bold,
                            )
                        ) {
                            append("Войти")
                        }
                    },
                    style = PulseFitType.BodyMedium,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onNavigateToLogin,
                    ),
                )
            }
        }
    }
}

@PreviewPhone
@Composable
private fun RegisterScreenPreview() {
    PreviewAppTheme {
        RegisterScreen(onBack = {}, onNavigateToLogin = {})
    }
}