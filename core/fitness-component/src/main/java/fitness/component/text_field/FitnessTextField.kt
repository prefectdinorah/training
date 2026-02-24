package fitness.component.text_field

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import fitness.theme.PulseFitColors
import fitness.theme.PulseFitType

@Composable
fun FitnessTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null,
) {
    val accent = if (isSystemInDarkTheme()) PulseFitColors.Accent else PulseFitColors.AccentDark
    val textPrimary = MaterialTheme.colorScheme.onBackground
    val textHint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
    val inputBg = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.06f)
    val inputBorder = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f)
    val inputFocusBg = accent.copy(alpha = 0.06f)
    val inputFocusBorder = accent.copy(alpha = 0.35f)

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val bgColor by animateColorAsState(
        targetValue = if (isFocused) inputFocusBg else inputBg,
        animationSpec = tween(300),
        label = "inputBg",
    )
    val borderColor by animateColorAsState(
        targetValue = if (isFocused) inputFocusBorder else inputBorder,
        animationSpec = tween(300),
        label = "inputBorder",
    )
    val iconTint by animateColorAsState(
        targetValue = if (isFocused) accent else textHint,
        animationSpec = tween(300),
        label = "iconTint",
    )

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        singleLine = true,
        textStyle = PulseFitType.BodyLarge.copy(color = textPrimary),
        cursorBrush = SolidColor(accent),
        visualTransformation = if (isPassword && !passwordVisible)
            PasswordVisualTransformation() else VisualTransformation.None,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(bgColor)
                    .border(1.dp, borderColor, RoundedCornerShape(16.dp))
                    .padding(horizontal = 18.dp),
            ) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp),
                )
                Spacer(Modifier.width(12.dp))

                Box(Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = PulseFitType.BodyLarge.copy(color = textHint),
                        )
                    }
                    innerTextField()
                }

                if (isPassword && onTogglePasswordVisibility != null) {
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        imageVector = if (passwordVisible) Icons.Rounded.Visibility
                        else Icons.Rounded.VisibilityOff,
                        contentDescription = if (passwordVisible) "Скрыть пароль" else "Показать пароль",
                        tint = textHint,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) { onTogglePasswordVisibility() },
                    )
                }
            }
        },
    )
}

@PreviewPhone
@Composable
private fun FitnessTextFieldPreview() {
    PreviewAppTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            var text by remember { mutableStateOf("") }
            FitnessTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = "Email",
                leadingIcon = Icons.Rounded.Email,
            )
        }
    }
}

@PreviewPhone
@Composable
private fun FitnessTextFieldPasswordPreview() {
    PreviewAppTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            FitnessTextField(
                value = "password123",
                onValueChange = {},
                placeholder = "Пароль",
                leadingIcon = Icons.Rounded.Lock,
                isPassword = true,
                passwordVisible = false,
                onTogglePasswordVisibility = {},
            )
        }
    }
}