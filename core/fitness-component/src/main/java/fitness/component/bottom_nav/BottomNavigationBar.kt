package fitness.component.bottom_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import fitness.component.R
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItem: BottomNavigationItem,
    onItemSelected: (BottomNavigationItem) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 24.dp, end = 16.dp, start = 16.dp)
            .clip(shape = RoundedCornerShape(size = 16.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach { item ->
                BottomNavItem(
                    item = item,
                    selected = item == selectedItem,
                    onClick = { onItemSelected(item) }
                )
            }
        }
    }
}

private enum class PreviewNavItem(
    override val title: Int,
    override val selectedIcon: ImageVector,
    override val unselectedIcon: ImageVector,
) : BottomNavigationItem {
    Home(R.string.core_nav_home, Icons.Filled.Home, Icons.Outlined.Home),
    Workout(R.string.core_nav_workout, Icons.Filled.FitnessCenter, Icons.Outlined.FitnessCenter),
    Profile(R.string.core_nav_profile, Icons.Filled.Person, Icons.Outlined.Person),
    Settings(R.string.core_nav_settings, Icons.Filled.Settings, Icons.Outlined.Settings),
}

@PreviewPhone
@Composable
private fun BottomNavigationBarPreview() {
    PreviewAppTheme {
        var selected by remember { mutableStateOf<BottomNavigationItem>(PreviewNavItem.Home) }

        BottomNavigationBar(
            items = PreviewNavItem.entries,
            selectedItem = selected,
            onItemSelected = { selected = it }
        )
    }
}
