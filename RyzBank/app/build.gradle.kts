import Libraries.addCompose
import Libraries.addCore
import Libraries.addHilt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {

    namespace = AndroidConfig.applicationId

    buildTypes {
        named("debug") {
            applicationIdSuffix = ".debug"
        }
        named("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libraries.Versions.composeUi
    }
}

dependencies {
    implementation(project(":RyzBank:domain"))
    implementation(project(":RyzBank:data"))
    implementation(project(":RyzBank:presentation"))

    addCore()
    addCompose()
    addHilt()
}