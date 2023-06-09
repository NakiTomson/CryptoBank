import Libraries.addCompose
import Libraries.addCore

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "core"

}

dependencies {
    addCore()
    implementation(Libraries.datastorePreferences)
}
