package support.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import fitness.component.bottom_nav.BottomNavigationItem

/**
 * Управляет только переключением табов.
 * Навигация внутри каждой фичи — задача [FeatureNavigator].
 */
class MainRouter(startTab: BottomNavigationItem) {

    private val tabHistory: SnapshotStateList<BottomNavigationItem> =
        mutableListOf(startTab).toMutableStateList()

    val currentTab: BottomNavigationItem get() = tabHistory.last()

    /** true — если есть куда вернуться по истории табов */
    val canGoBack: Boolean
        get() = tabHistory.size > 1

    fun switchTab(tab: BottomNavigationItem) {
        if (currentTab == tab) return
        tabHistory.add(tab)
    }

    /** Возвращает false, если история табов пуста — нужно выйти из приложения */
    fun back(): Boolean {
        if (!canGoBack) return false
        tabHistory.removeAt(tabHistory.size - 1)
        return true
    }
}

@Composable
fun rememberMainRouter(startTab: BottomNavigationItem): MainRouter = remember {
    MainRouter(startTab = startTab)
}