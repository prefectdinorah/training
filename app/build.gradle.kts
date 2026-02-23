plugins {
    id("training.android.application.compose")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.training"

    defaultConfig {
        applicationId = "com.example.training"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":core:fitness-component"))
    implementation(project(":support:navigation"))
}