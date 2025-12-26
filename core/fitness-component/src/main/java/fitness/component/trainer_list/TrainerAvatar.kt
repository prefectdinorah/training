package fitness.component.trainer_list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme

@Composable
fun TrainerAvatar(
    image: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size = 72.dp)
            .border(
                width = 2.dp,
                color = Color.DarkGray.copy(alpha = 0.3f),
                shape = CircleShape
            )
            .padding(all = 3.dp),
        contentAlignment = Alignment.Center
    ) {
        image()
    }
}

@PreviewPhone
@Composable
private fun TrainerAvatarPreview() {
    PreviewAppTheme {
        TrainerAvatar(
            image = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    tint = Color.Black
                )
            }
        )
    }
}