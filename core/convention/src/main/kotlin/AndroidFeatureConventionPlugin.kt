import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Плагин для feature-модулей.
 * Расширяет [AndroidLibraryComposeConventionPlugin].
 *
 * Применяет:
 * - training.android.library.compose (Compose + базовая настройка)
 * - com.google.devtools.ksp
 *
 * Добавляет:
 * - Dagger (DI)
 * - Navigation3 (навигация)
 * - Lifecycle Runtime KTX
 *
 * Использование:
 * ```kotlin
 * plugins {
 *     id("training.android.feature")
 * }
 * ```
 *
 * Опциональные зависимости (добавить вручную при необходимости):
 * - Room (для фичей с локальной БД)
 * - Retrofit/OkHttp (для фичей с сетью)
 * - Coil (для фичей с изображениями)
 * - kotlinx-serialization (для фичей с JSON)
 */
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("training.android.library.compose")
            pluginManager.apply("com.google.devtools.ksp")

            val libs = libs()
            dependencies {
                // DI
                add("implementation", libs.findLibrary("dagger-core").get())
                add("ksp", libs.findLibrary("dagger-compiler").get())

                // Navigation
                add("implementation", libs.findLibrary("compose-navigation-runtime").get())
                add("implementation", libs.findLibrary("compose-navigation-ui").get())

                // Lifecycle
                add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
            }
        }
    }
}