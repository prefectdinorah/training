package fitness.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import fitness.theme.PulseFitColors
import fitness.theme.PulseFitType

@Composable
fun FitnessAccentButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val isDark = isSystemInDarkTheme()
    val bgBrush = if (enabled)
        Brush.linearGradient(listOf(PulseFitColors.Accent, PulseFitColors.AccentDark))
    else
        SolidColor(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))

    val accentForText = if (isDark) PulseFitColors.Accent else PulseFitColors.AccentDark
    val textColor = if (enabled) Color(0xFF111111) else accentForText.copy(alpha = .4f)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(bgBrush)
            .clickable(enabled = enabled, onClick = onClick),
    ) {
        Text(
            text = text,
            style = PulseFitType.Button.copy(color = textColor),
        )
    }
}

@Composable
fun FitnessGhostButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val ghostBg = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.06f)
    val ghostBorder = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f)
    val ghostText = MaterialTheme.colorScheme.onBackground

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(ghostBg)
            .border(1.dp, ghostBorder, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
    ) {
        Text(
            text = text,
            style = PulseFitType.Button.copy(color = ghostText),
        )
    }
}

@PreviewPhone
@Composable
private fun FitnessButtonsPreview() {
    PreviewAppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            FitnessAccentButton(text = "Создать аккаунт", onClick = {})
            FitnessAccentButton(text = "Недоступно", onClick = {}, enabled = false)
            FitnessGhostButton(text = "Уже есть аккаунт", onClick = {})
        }
    }
}