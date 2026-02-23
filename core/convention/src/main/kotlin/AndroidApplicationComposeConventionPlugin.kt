import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * Плагин для app-модуля с полным стеком зависимостей.
 * Расширяет [AndroidApplicationConventionPlugin].
 *
 * Применяет:
 * - training.android.application (базовая настройка)
 * - org.jetbrains.kotlin.plugin.compose
 * - com.google.devtools.ksp
 *
 * Добавляет:
 * - Compose (BOM, UI, Material3, Icons, Activity, Immutable Collections)
 * - Dagger (DI)
 * - Navigation3
 * - Retrofit + OkHttp (сеть)
 * - Room (локальная БД)
 * - Coil (загрузка изображений)
 *
 * Использование:
 * ```kotlin
 * plugins {
 *     id("training.android.application.compose")
 * }
 * ```
 *
 * Опциональные зависимости (добавить вручную при необходимости):
 * - kotlinx-serialization
 */
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("training.android.application")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
            pluginManager.apply("com.google.devtools.ksp")

            extensions.configure<ApplicationExtension> {
                buildFeatures {
                    compose = true
                }
            }

            val libs = libs()
            dependencies {
                // Compose
                val bom = libs.findLibrary("androidx-compose-bom").get()
                add("implementation", platform(bom))
                add("debugImplementation", platform(bom))

                add("implementation", libs.findLibrary("androidx-activity-compose").get())
                add("implementation", libs.findLibrary("androidx-compose-ui").get())
                add("implementation", libs.findLibrary("androidx-compose-ui-graphics").get())
                add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
                add("implementation", libs.findLibrary("androidx-compose-material3").get())
                add("implementation", libs.findLibrary("androidx-compose-material-icons-core").get())
                add("implementation", libs.findLibrary("androidx-compose-material-icons-extended").get())
                add("implementation", libs.findLibrary("kotlin-immutable-collections").get())

                add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
                add("debugImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())

                // DI
                add("implementation", libs.findLibrary("dagger-core").get())
                add("ksp", libs.findLibrary("dagger-compiler").get())

                // Navigation
                add("implementation", libs.findLibrary("compose-navigation-runtime").get())
                add("implementation", libs.findLibrary("compose-navigation-ui").get())

                // Network
                add("implementation", libs.findLibrary("retrofit-core").get())
                add("implementation", libs.findLibrary("retrofit-convertor").get())
                add("implementation", libs.findLibrary("okHttpClient-logging").get())

                // Database
                add("implementation", libs.findLibrary("room-ktx").get())
                add("ksp", libs.findLibrary("room-compiler").get())

                // Images
                add("implementation", libs.findLibrary("compose-coil").get())
                add("implementation", libs.findLibrary("compose-coil-network").get())
            }
        }
    }
}