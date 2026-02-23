plugins {
    id("training.android.library.compose")
}

android {
    namespace = "fitness.component"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}