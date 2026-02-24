package fitness.component.text_field.atom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitness.component.R
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import fitness.theme.PulseFitColors
import fitness.theme.PulseFitType

/**
 * Индикатор надёжности пароля.
 *
 * Показывает 4 полосы: слабый (1) → средний (2) → надёжный (3-4).
 * Цвет меняется через [fitness.theme.PulseFitColors.Orange] → [fitness.theme.PulseFitColors.Yellow] → [fitness.theme.PulseFitColors.Accent].
 */
@Composable
fun PasswordStrengthIndicator(password: String) {
    val strength = when {
        password.length >= 12 -> 4
        password.length >= 8 -> 3
        password.length >= 4 -> 2
        password.isNotEmpty() -> 1
        else -> 0
    }

    val accent = if (isSystemInDarkTheme()) PulseFitColors.Accent else PulseFitColors.Green
    val strengthColor = when (strength) {
        1 -> PulseFitColors.Orange
        2 -> PulseFitColors.Yellow
        else -> accent
    }

    val strengthText = when (strength) {
        1 -> stringResource(id = R.string.core_password_strength_low)
        2 -> stringResource(id = R.string.core_password_strength_medium)
        3, 4 -> stringResource(id = R.string.core_password_strength_high)
        else -> ""
    }

    AnimatedVisibility(
        visible = password.isNotEmpty(),
        enter = fadeIn() + expandVertically(),
    ) {
        Column {
            Spacer(Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                repeat(4) { i ->
                    val active = i < strength
                    val barColor by animateColorAsState(
                        targetValue = if (active) strengthColor else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                        animationSpec = tween(300),
                        label = "bar$i",
                    )
                    Box(
                        Modifier
                            .weight(1f)
                            .height(3.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(barColor)
                    )
                }
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = strengthText,
                style = PulseFitType.LabelSmall.copy(
                    color = strengthColor,
                    letterSpacing = 0.sp,
                ),
            )
        }
    }
}

@PreviewPhone
@Composable
private fun PasswordStrengthWeakPreview() {
    PreviewAppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            PasswordStrengthIndicator(password = "abc")        // слабый
        }
    }
}

@PreviewPhone
@Composable
private fun PasswordStrengthMediumPreview() {
    PreviewAppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            PasswordStrengthIndicator(password = "abcd1234")   // средний
        }
    }
}

@PreviewPhone
@Composable
private fun PasswordStrengthStrongPreview() {
    PreviewAppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            PasswordStrengthIndicator(password = "abcd1234!@#$") // надёжный
        }
    }
}