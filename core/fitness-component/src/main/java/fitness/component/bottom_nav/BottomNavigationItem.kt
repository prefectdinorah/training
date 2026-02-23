package fitness.component.bottom_nav

import androidx.compose.ui.graphics.vector.ImageVector

interface BottomNavigationItem {
    val title: Int
    val selectedIcon: ImageVector
    val unselectedIcon: ImageVector
}