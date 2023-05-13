import Libraries.addCompose
import Libraries.addCore
import Libraries.addFirebaseBom
import Libraries.addHilt

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
        kotlinCompilerExtensionVersion = Libraries.Versions.compose
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(Libraries.firebaseServicesAuth)
    addCore()
    addCompose()
    addHilt()
    addFirebaseBom()
    implementation(Libraries.accompanistPager)
    implementation(Libraries.accompanistIndicators)
    implementation(Libraries.accompanistPermissions)
}