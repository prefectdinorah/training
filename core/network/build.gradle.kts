plugins {
    id("training.android.library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "core.network"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.example.com/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://api.example.com/\"")
        }
    }
}

dependencies {
    implementation(project(":core:coroutine"))
    api(project(":core:datastore"))
    api(libs.dagger.core)
    ksp(libs.dagger.compiler)

    // Ktor
    api(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.auth)
}