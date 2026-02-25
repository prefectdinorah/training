plugins {
    id("training.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "core.datastore"
}

dependencies {
    implementation(project(":core:coroutine"))
    api(libs.dagger.core)
    ksp(libs.dagger.compiler)
    implementation(libs.androidx.security.crypto)
}