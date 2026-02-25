plugins {
    id("training.android.feature")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "feature.main.impl"
}

dependencies {
    api(project(":feature:main:api"))
    implementation(project(":support:navigation"))
    implementation(project(":core:fitness-component"))
    implementation(project(":core:di"))
    implementation(project(":feature:workout-creation:api"))
}