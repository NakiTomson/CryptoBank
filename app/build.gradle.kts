import Libraries.addCompose
import Libraries.addCore
import Libraries.addFirebaseBom
import Libraries.addHilt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
}

android {

    namespace = AndroidConfig.applicationId

    buildTypes {
        named("debug") {
//            applicationIdSuffix = ".debug"
        }
        named("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }
    flavorDimensions += "default"

    productFlavors {
        create("dev") {
            dimension = "default"
            applicationId = "${AndroidConfig.applicationId}"
            buildConfigField("String", "BASE_URL", "\"https://645bcdcfa8f9e4d6e77393a3.mockapi.io\"")
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libraries.Versions.compose
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":presentation"))

    addCore()
    addCompose()
    addHilt()
    addFirebaseBom()
}