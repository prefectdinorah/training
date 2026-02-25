plugins {
    id("training.android.library.compose")
}

android {
    namespace = "feature.auth.api"
}

dependencies {
    api(project(":support:navigation"))
}