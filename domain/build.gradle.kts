import Libraries.addCore
import Libraries.addHilt

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "domain"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    addCore()
    addHilt()
}