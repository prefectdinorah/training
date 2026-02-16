package fitness.component.category_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme

@Composable
internal fun CategoryItem(
    icon: ImageVector,
    contentDescription: String,
    categoryName: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 1.dp
            )
        ) {
            Icon(
                modifier = Modifier.padding(all = 16.dp),
                imageVector = icon,
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.surfaceVariant
            )
        }

        Text(
            text = categoryName,
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@PreviewPhone
@Composable
private fun CategoryItemPreview() {
    PreviewAppTheme {
        CategoryItem(icon = Icons.Default.Android, contentDescription = "", categoryName = "Android")
    }
}
