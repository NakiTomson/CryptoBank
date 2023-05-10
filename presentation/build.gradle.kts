import Libraries.addCompose
import Libraries.addCore
import Libraries.addHilt
import Libraries.addNavigation

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "presentation"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libraries.Versions.composeUi
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha04")
    addCore()
    addCompose()
    addHilt()
}