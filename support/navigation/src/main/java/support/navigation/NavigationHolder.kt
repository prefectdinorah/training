package support.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation3.runtime.NavKey

/**
 * Мост между Compose-навигацией и DI.
 *
 * Живёт в DI-графе (инжектится в launcher'ы), а [FeatureNavigator] подключается
 * из Compose через [bind] и отключается автоматически при уходе с экрана.
 *
 * Использование в Compose:
 * ```kotlin
 * @Composable
 * fun WorkoutCreationHost(navigator: FeatureNavigator<WorkoutNavKey>) {
 *     val component = getComponent<WorkoutCreationApi>()
 *     component.navigationHolder().bind(navigator)
 * }
 * ```
 *
 * Использование в launcher:
 * ```kotlin
 * class WorkoutCreationLauncher @Inject constructor(
 *     private val navigationHolder: NavigationHolder,
 * ) : IWorkoutCreationLauncher {
 *     override fun open() = navigationHolder.navigate(WorkoutCreationKey)
 * }
 * ```
 */
class NavigationHolder : NavigationApi {

    @Volatile
    private var navigator: FeatureNavigator<NavKey>? = null

    override fun navigationHolder(): NavigationHolder = this

    @Suppress("UNCHECKED_CAST")
    fun <T : NavKey> attach(navigator: FeatureNavigator<T>) {
        this.navigator = navigator as FeatureNavigator<NavKey>
    }

    fun detach() {
        navigator = null
    }

    fun navigate(key: NavKey) {
        navigator?.navigate(key)
    }

    fun back(): Boolean = navigator?.back() ?: false
}

/**
 * Подключает [FeatureNavigator] к [NavigationHolder] на время жизни Composable.
 * Отключает автоматически через [DisposableEffect].
 */
@Composable
fun <T : NavKey> NavigationHolder.bind(navigator: FeatureNavigator<T>) {
    DisposableEffect(navigator) {
        attach(navigator)
        onDispose { detach() }
    }
}