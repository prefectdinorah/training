package feature.auth.impl.presentation.screen.login.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import core.viewmodel.getViewModel
import feature.auth.impl.R
import feature.auth.impl.di.component.login.LoginScreenComponent
import feature.auth.impl.presentation.screen.login.model.LoginEffect
import feature.auth.impl.presentation.screen.login.model.LoginIntent
import feature.auth.impl.presentation.screen.login.viewmodel.LoginViewModel
import fitness.component.button.FitnessAccentButton
import fitness.component.button.SocialButton
import fitness.component.divider.OrDivider
import fitness.component.text_field.FitnessTextField
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import fitness.theme.PulseFitColors
import fitness.theme.PulseFitType

@Composable
internal fun LoginScreen(
    onBack: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onLogin: () -> Unit,
) {
    val viewModel: LoginViewModel = getViewModel<LoginScreenComponent, LoginViewModel>()
    val state by viewModel.viewState.collectAsState()
    val sendIntent = viewModel::sendIntent


    val accent = if (isSystemInDarkTheme()) PulseFitColors.Accent else PulseFitColors.AccentDark
    val background = MaterialTheme.colorScheme.background
    val textPrimary = MaterialTheme.colorScheme.onBackground
    val textSecondary = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
    val inputBg = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.06f)
    val inputBorder = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f)
    val glowColor = accent.copy(alpha = .07f)

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                LoginEffect.Success -> onLogin()
                is LoginEffect.Error -> {}
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .drawBehind {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(glowColor, Color.Transparent),
                        center = Offset(size.width / 2, 0f),
                        radius = size.width * 0.7f,
                    ),
                    center = Offset(size.width / 2, 0f),
                    radius = size.width * 0.7f,
                )
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(PulseFitColors.Teal.copy(alpha = .04f), Color.Transparent),
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
                    Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.auth_cd_back),
                    tint = textPrimary,
                    modifier = Modifier.size(20.dp),
                )
            }

            Spacer(Modifier.height(28.dp))

            Text(
                text = stringResource(R.string.auth_login_title),
                style = PulseFitType.HeadlineMedium.copy(color = textPrimary),
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.auth_login_subtitle),
                style = PulseFitType.BodyLarge.copy(color = textSecondary),
            )

            Spacer(Modifier.height(32.dp))

            FitnessTextField(
                value = state.email,
                onValueChange = { sendIntent(LoginIntent.EmailChanged(it)) },
                placeholder = stringResource(R.string.auth_field_hint_email),
                leadingIcon = Icons.Rounded.Email,
            )

            Spacer(Modifier.height(14.dp))

            FitnessTextField(
                value = state.password,
                onValueChange = { sendIntent(LoginIntent.PasswordChanged(it)) },
                placeholder = stringResource(R.string.auth_field_hint_password),
                leadingIcon = Icons.Rounded.Lock,
                isPassword = true,
                passwordVisible = state.passwordVisible,
                onTogglePasswordVisibility = { sendIntent(LoginIntent.TogglePasswordVisibility) },
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.auth_login_forgot_password),
                style = PulseFitType.LabelMedium.copy(color = accent),
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { /* TODO */ },
            )

            Spacer(Modifier.height(24.dp))

            FitnessAccentButton(
                text = stringResource(R.string.auth_action_login),
                enabled = state.email.isNotBlank() && state.password.isNotBlank() && !state.isLoading,
                onClick = { sendIntent(LoginIntent.Submit) },
            )

            Spacer(Modifier.height(28.dp))

            OrDivider()

            Spacer(Modifier.height(28.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SocialButton(label = stringResource(R.string.auth_social_google), icon = "G", modifier = Modifier.weight(1f))
                SocialButton(label = stringResource(R.string.auth_social_apple),  icon = "",  modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.weight(1f))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 44.dp),
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = textSecondary)) {
                            append(stringResource(R.string.auth_login_no_account_prefix))
                        }
                        withStyle(SpanStyle(color = accent, fontWeight = FontWeight.Bold)) {
                            append(stringResource(R.string.auth_login_register_link))
                        }
                    },
                    style = PulseFitType.BodyMedium,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onNavigateToRegister,
                    ),
                )
            }
        }
    }
}

@PreviewPhone
@Composable
private fun LoginScreenPreview() {
    PreviewAppTheme {
        LoginScreen(onBack = {}, onNavigateToRegister = {}, onLogin = {})
    }
}