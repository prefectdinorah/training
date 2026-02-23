package support.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavKey

/**
 * Управляет навигацией внутри одной фичи (таба).
 * Каждый таб получает свой экземпляр через [rememberFeatureNavigator].
 *
 * Использование в фиче:
 * ```kotlin
 * val navigator = rememberFeatureNavigator(WorkoutListKey)
 *
 * NavDisplay(
 *     backStack = navigator.backStack,
 *     onBack = { navigator.back() },
 *     entryProvider = entryProvider {
 *         entry<WorkoutListKey> { WorkoutListScreen(navigator) }
 *         entry<WorkoutDetailKey> { key -> WorkoutDetailScreen(key.id, navigator) }
 *     }
 * )
 * ```
 */
class FeatureNavigator<T : NavKey>(startKey: T) {

    val backStack: SnapshotStateList<T> = mutableListOf(startKey).toMutableStateList()

    /** Перейти на новый экран */
    fun navigate(key: T) {
        backStack.add(key)
    }

    /** Назад. Возвращает false если уже на стартовом экране */
    fun back(): Boolean {
        if (backStack.size <= 1) return false
        backStack.removeAt(backStack.size - 1)
        return true
    }

    /** Заменить весь стек (например, для deep link) */
    fun replaceAll(vararg keys: T) {
        backStack.clear()
        backStack.addAll(keys.toList())
    }
}

@Composable
fun <T : NavKey> rememberFeatureNavigator(startKey: T): FeatureNavigator<T> = remember {
    FeatureNavigator(startKey = startKey)
}