plugins {
    id("training.android.feature")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "feature.auth.impl"
}

dependencies {
    api(project(":feature:auth:api"))
    implementation(project(":core:di"))
    implementation(project(":core:mvi"))
    implementation(project(":core:coroutine"))
    implementation(project(":core:network"))
    implementation(project(":support:navigation"))
    implementation(project(":core:fitness-component"))
}