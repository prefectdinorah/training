package feature.auth.impl.presentation.screen.registration.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.viewmodel.getViewModel
import feature.auth.impl.R
import feature.auth.impl.di.component.registration.RegistrationScreenComponent
import feature.auth.impl.presentation.screen.registration.model.RegistrationEffect
import feature.auth.impl.presentation.screen.registration.model.RegistrationIntent
import feature.auth.impl.presentation.screen.registration.model.RegistrationState
import feature.auth.impl.presentation.screen.registration.viewmodel.RegistrationViewModel
import fitness.component.button.FitnessAccentButton
import fitness.component.button.SocialButton
import fitness.component.divider.OrDivider
import fitness.component.text_field.FitnessTextField
import fitness.component.text_field.atom.PasswordStrengthIndicator
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import fitness.theme.PulseFitColors
import fitness.theme.PulseFitType

@Composable
internal fun RegisterScreen(
    onBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onRegister: () -> Unit,
) {
    val viewModel: RegistrationViewModel = getViewModel<RegistrationScreenComponent, RegistrationViewModel>()
    val state by viewModel.viewState.collectAsState()
    val sendIntent = viewModel::sendIntent

    RegisterContent(
        onBack = onBack,
        onNavigateToLogin = onNavigateToLogin,
        onRegister = onRegister,
        viewModel = viewModel,
        sendIntent = sendIntent,
        state = state,
    )
}

@Composable
private fun RegisterContent(
    onBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onRegister: () -> Unit,
    viewModel: RegistrationViewModel,
    sendIntent: (RegistrationIntent) -> Unit,
    state: RegistrationState,
) {
    val accent = if (isSystemInDarkTheme()) PulseFitColors.Accent else PulseFitColors.AccentDark
    val background = MaterialTheme.colorScheme.background
    val textPrimary = MaterialTheme.colorScheme.onBackground
    val textSecondary = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
    val textHint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
    val inputBg = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.06f)
    val inputBorder = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f)
    val glowColor = accent.copy(alpha = .07f)

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                RegistrationEffect.Success -> onRegister()
                is RegistrationEffect.Error -> {}
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
                    contentDescription = stringResource(R.string.auth_cd_back),
                    tint = textPrimary,
                    modifier = Modifier.size(20.dp),
                )
            }

            Spacer(Modifier.height(28.dp))

            Text(
                text = stringResource(R.string.auth_register_title),
                style = PulseFitType.HeadlineMedium.copy(color = textPrimary),
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.auth_register_subtitle),
                style = PulseFitType.BodyLarge.copy(color = textSecondary),
            )

            Spacer(Modifier.height(32.dp))

            FitnessTextField(
                value = state.name,
                onValueChange = { sendIntent(RegistrationIntent.NameChanged(it)) },
                placeholder = stringResource(R.string.auth_field_hint_name),
                leadingIcon = Icons.Rounded.Person,
            )

            Spacer(Modifier.height(14.dp))

            FitnessTextField(
                value = state.email,
                onValueChange = { sendIntent(RegistrationIntent.EmailChanged(it)) },
                placeholder = stringResource(R.string.auth_field_hint_email),
                leadingIcon = Icons.Rounded.Email,
            )

            Spacer(Modifier.height(14.dp))

            FitnessTextField(
                value = state.password,
                onValueChange = { sendIntent(RegistrationIntent.PasswordChanged(it)) },
                placeholder = stringResource(R.string.auth_field_hint_password),
                leadingIcon = Icons.Rounded.Lock,
                isPassword = true,
                passwordVisible = state.passwordVisible,
                onTogglePasswordVisibility = { sendIntent(RegistrationIntent.TogglePasswordVisibility) },
            )

            PasswordStrengthIndicator(password = state.password)

            Spacer(Modifier.height(24.dp))

            FitnessAccentButton(
                text = stringResource(R.string.auth_register_btn_submit),
                enabled = state.name.isNotBlank() && state.email.isNotBlank() && state.password.isNotBlank() && !state.isLoading,
                onClick = { sendIntent(RegistrationIntent.Submit) },
            )

            Spacer(Modifier.height(24.dp))

            OrDivider()

            Spacer(Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SocialButton(label = stringResource(R.string.auth_social_google), icon = "", modifier = Modifier.weight(1f))
                SocialButton(label = stringResource(R.string.auth_social_apple),  icon = "",  modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = textHint)) {
                        append(stringResource(R.string.auth_register_terms_prefix))
                    }
                    withStyle(SpanStyle(color = textSecondary)) {
                        append(stringResource(R.string.auth_register_terms_link))
                    }
                    withStyle(SpanStyle(color = textHint)) {
                        append(stringResource(R.string.auth_register_terms_conjunction))
                    }
                    withStyle(SpanStyle(color = textSecondary)) {
                        append(stringResource(R.string.auth_register_privacy_link))
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
                            append(stringResource(R.string.auth_register_have_account_prefix))
                        }
                        withStyle(SpanStyle(color = accent, fontWeight = FontWeight.Bold)) {
                            append(stringResource(R.string.auth_action_login))
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
        RegisterScreen(onBack = {}, onNavigateToLogin = {}, onRegister = {})
    }
}