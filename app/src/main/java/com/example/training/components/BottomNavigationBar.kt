package com.example.training.components

import androidx.compose.runtime.Composable
import com.example.training.navigation.NavigationItem.DashboardItem
import com.example.training.navigation.NavigationItem.ProfileItem
import com.example.training.navigation.NavigationItem.SettingsItem
import com.example.training.navigation.NavigationItem.WorkoutItem
import support.navigation.MainRouter
import fitness.component.bottom_nav.BottomNavigationBar
import fitness.component.bottom_nav.BottomNavigationItem

@Composable
fun BottomNavigationBar(router: MainRouter) {
    val items: List<BottomNavigationItem> = listOf(DashboardItem, WorkoutItem, ProfileItem, SettingsItem)

    BottomNavigationBar(
        items = items,
        selectedItem = router.currentTab,
        onItemSelected = { router.switchTab(it) }
    )
}