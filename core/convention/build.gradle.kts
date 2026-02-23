import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.example.training.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        // --- Core модули ---
        register("androidLibrary") {
            id = "training.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "training.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        // --- Feature модули ---
        register("androidFeature") {
            id = "training.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        // --- App модуль ---
        register("androidApplication") {
            id = "training.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "training.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
    }
}