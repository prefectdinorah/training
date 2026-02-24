plugins {
    id("training.android.feature")
}

android {
    namespace = "feature.workoutcreation.impl"
}

dependencies {
    api(project(":feature:workout-creation:api"))
    implementation(project(":core:di"))
    implementation(project(":support:navigation"))
}