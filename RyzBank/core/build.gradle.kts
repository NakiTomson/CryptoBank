import Libraries.addCore

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = AndroidConfig.applicationId
}

dependencies {
    addCore()
}
