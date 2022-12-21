import Libraries.addCore
import Libraries.addHilt

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "data"
}

dependencies {
    implementation(project(":RyzBank:core"))
    implementation(project(":RyzBank:data-source:local"))
    implementation(project(":RyzBank:data-source:remote"))
    implementation(project(":RyzBank:domain"))

    addCore()
    addHilt()
}