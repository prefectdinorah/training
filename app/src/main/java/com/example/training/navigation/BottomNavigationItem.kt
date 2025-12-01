package com.example.training.navigation

import androidx.compose.ui.graphics.vector.ImageVector

interface BottomNavigationItem {
    val title: Int
    val selectedIcon: ImageVector
    val unselectedIcon: ImageVector
}