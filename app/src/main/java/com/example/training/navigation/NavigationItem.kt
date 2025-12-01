package com.example.training.navigation

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.example.training.R
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavigationItem {
    @Serializable
    data object DashboardItem : NavigationItem, NavKey, BottomNavigationItem {
        override val title: Int = R.string.navigation_item_dashboard
        override val selectedIcon: ImageVector = Filled.Restaurant
        override val unselectedIcon: ImageVector = Outlined.Restaurant
    }
    @Serializable
    data object WorkoutItem : NavigationItem, NavKey, BottomNavigationItem {
        override val title: Int = R.string.navigation_item_workout
        override val selectedIcon: ImageVector = Filled.FitnessCenter
        override val unselectedIcon: ImageVector = Outlined.FitnessCenter
    }
    @Serializable
    data object ProfileItem : NavigationItem, NavKey, BottomNavigationItem {
        override val title: Int = R.string.navigation_item_profile
        override val selectedIcon: ImageVector = Filled.Person
        override val unselectedIcon: ImageVector = Outlined.Person
    }
    @Serializable
    data object SettingsItem : NavigationItem, NavKey, BottomNavigationItem {
        override val title: Int = R.string.navigation_item_settings
        override val selectedIcon: ImageVector = Filled.Settings
        override val unselectedIcon: ImageVector = Outlined.Settings
    }
}