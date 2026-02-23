plugins {
    id("training.android.library.compose")
}

android {
    namespace = "support.navigation"
}

dependencies {
    implementation(project(":core:fitness-component"))
    implementation(libs.compose.navigation.runtime)
}