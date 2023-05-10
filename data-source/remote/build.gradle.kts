import Libraries.addCore
import Libraries.addHilt
import Libraries.addNetworkDependencies

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "remote"
}

dependencies {
    implementation(project(":core"))
    addCore()
    addHilt()
    addNetworkDependencies()
}