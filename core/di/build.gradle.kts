plugins {
    id("training.android.library.compose")
}

android {
    namespace = "core.di"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.dagger.core)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}