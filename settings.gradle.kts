pluginManagement {
    includeBuild("core/convention")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Training"
include(":app")
include(":core:fitness-component")
include(":support:navigation")
include(":core:mvi")
include(":core:di")
include(":feature:workout-creation:api")
include(":feature:workout-creation:impl")