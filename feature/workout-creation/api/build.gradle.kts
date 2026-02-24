plugins {
    id("training.android.library.compose")
}

android {
    namespace = "feature.workoutcreation.api"
}

dependencies {
    api(project(":support:navigation"))
}