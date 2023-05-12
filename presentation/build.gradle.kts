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
    addCore()
    addCompose()
    addHilt()
    implementation(Libraries.accompanistPager)
    implementation(Libraries.accompanistIndicators)
    implementation(Libraries.accompanistPermissions)
}