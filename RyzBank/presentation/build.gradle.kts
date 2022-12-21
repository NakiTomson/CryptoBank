import Libraries.addCompose
import Libraries.addCore
import Libraries.addHilt
import Libraries.addNavigation

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
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
    implementation(project(":RyzBank:domain"))
    implementation(project(":RyzBank:core"))
    addCore()
    addCompose()
    addNavigation()
    addHilt()
}