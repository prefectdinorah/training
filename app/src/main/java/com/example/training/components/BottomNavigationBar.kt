package com.example.training.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.example.training.navigation.NavigationItem.DashboardItem
import com.example.training.navigation.NavigationItem.ProfileItem
import com.example.training.navigation.NavigationItem.SettingsItem
import com.example.training.navigation.NavigationItem.WorkoutItem
import com.example.training.router.MainRouter
import com.example.training.router.rememberMainRouter

@Composable
fun BottomNavigationBar(router: MainRouter<NavKey>) {
    val bottomNavigationItemList = listOf(
        DashboardItem,
        WorkoutItem,
        ProfileItem,
        SettingsItem
    )

    Row(
        modifier = Modifier
            .padding(bottom = 24.dp, end = 16.dp, start = 16.dp)
            .clip(shape = CircleShape)
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            bottomNavigationItemList.forEach { item ->
                val selected = router.mainKey == item
                BottomNavItem(
                    icon = if (selected) item.selectedIcon else item.unselectedIcon,
                    title = item.title,
                    selected = selected,
                    onClick = { router.switchTopLevel(key = item) },
                )
            }
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar(router = rememberMainRouter(DashboardItem))
}
