package feature.main.impl.presentation.components

import androidx.compose.runtime.Composable
import feature.main.impl.navigation.NavigationItem.FoodItem
import feature.main.impl.navigation.NavigationItem.SettingsItem
import feature.main.impl.navigation.NavigationItem.TrainersItem
import feature.main.impl.navigation.NavigationItem.WorkoutItem
import fitness.component.bottom_nav.BottomNavigationBar
import fitness.component.bottom_nav.BottomNavigationItem
import support.navigation.MainRouter

@Composable
internal fun BottomNavigationBar(router: MainRouter) {
    val items: List<BottomNavigationItem> = listOf(WorkoutItem, FoodItem, TrainersItem, SettingsItem)

    BottomNavigationBar(
        items = items,
        selectedItem = router.currentTab,
        onItemSelected = { router.switchTab(it) },
    )
}