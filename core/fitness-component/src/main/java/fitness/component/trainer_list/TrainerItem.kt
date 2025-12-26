package fitness.component.trainer_list

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TrainerItem(
    item: ITrainerModel,
    avatar: @Composable BoxScope.() -> Unit
) {
    Column(
        modifier = Modifier.width(width = 96.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TrainerAvatar(image = avatar)

        Spacer(modifier = Modifier.height(height = 8.dp))

        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = item.category,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}