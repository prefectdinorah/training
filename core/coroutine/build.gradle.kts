plugins {
    id("training.android.library")
}

android {
    namespace = "core.coroutine"
}

dependencies {
    api(libs.androidx.lifecycle.viewmodel.ktx)
}