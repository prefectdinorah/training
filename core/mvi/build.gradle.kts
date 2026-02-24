plugins {
    id("training.android.library")
}

android {
    namespace = "core.mvi"
}

dependencies {
    api(libs.androidx.lifecycle.viewmodel.ktx)
}