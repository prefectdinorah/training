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
    implementation(project(":core:di"))
    implementation(project(":core:network"))
    implementation(project(":core:fitness-component"))
    implementation(project(":support:navigation"))
    implementation(project(":feature:workout-creation:impl"))
    implementation(project(":feature:auth:impl"))
    implementation(project(":feature:main:impl"))
}