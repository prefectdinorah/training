package fitness.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import fitness.theme.PulseFitType

@Composable
fun SocialButton(
    label: String,
    icon: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val surfaceColor = MaterialTheme.colorScheme.secondary
    val textColor = MaterialTheme.colorScheme.onBackground

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(50.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(surfaceColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = icon, fontSize = 18.sp)
            Spacer(Modifier.width(8.dp))
            Text(
                text = label,
                style = PulseFitType.LabelMedium.copy(color = textColor),
            )
        }
    }
}

@PreviewPhone
@Composable
private fun SocialButtonPreview() {
    PreviewAppTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            SocialButton(label = "Google", icon = "G", modifier = Modifier.weight(1f))
            SocialButton(label = "Apple", icon = "", modifier = Modifier.weight(1f))
        }
    }
}