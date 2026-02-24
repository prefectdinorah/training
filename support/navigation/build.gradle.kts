plugins {
    id("training.android.library.compose")
}

android {
    namespace = "support.navigation"
}

dependencies {
    implementation(project(":core:fitness-component"))
    api(libs.compose.navigation.runtime)
    implementation(libs.androidx.lifecycle.runtime.compose)
}