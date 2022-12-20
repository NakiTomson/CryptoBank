import org.gradle.api.artifacts.dsl.DependencyHandler

const val kotlinVersion = "1.7.20"

object AndroidConfig {
    const val applicationId = "com.example.ryzbank"
    const val compileSdk = 33
    const val minSdk = 23
    const val targetSdk = compileSdk
}


object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "7.3.0"
        const val googleServicesVersion = "4.3.14"
        const val appdistributionVersion = "3.0.3"
        const val crashlyticsVersion = "2.9.2"
        const val navigationVersion = "2.5.2"
        const val hiltVersion = "2.44"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val navigationGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val googleServicesGradlePlugin = "com.google.gms:google-services:${Versions.googleServicesVersion}"
    const val appdistributionGradlePlugin =
        "com.google.firebase:firebase-appdistribution-gradle:${Versions.appdistributionVersion}"
    const val crashlyticsGradlePlugin = "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlyticsVersion}"
}


object Libraries {

    object Versions {
        const val coreKtx = "1.9.0"
        const val coroutines = "1.6.2"
        const val composeUi = "1.3.2"
        const val composeActivity = "1.6.1"
        const val composeMaterial = "1.3.1"
    }

    //Kotlin
    private const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}"
    private const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    private const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    //Compose
    private const val composeUi = "androidx.compose.ui:ui:${Versions.composeUi}"
    private const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    private const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.composeUi}"
    private const val composeMaterial = "androidx.compose.material:material:${Versions.composeMaterial}"
    private const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.composeUi}"
    private const val composeTestManifest= "androidx.compose.ui:ui-test-manifest:${Versions.composeUi}"

    fun DependencyHandler.addCore() {
        implementation(kotlin)
        implementation(coroutines)
        implementation(coroutines_android)
        implementation(coreKtx)
    }

    fun DependencyHandler.addCompose() {
        implementation(composeUi)
        implementation(composeActivity)
        implementation(composeToolingPreview)
        implementation(composeMaterial)
        debugImplementation(composeTooling)
        debugImplementation(composeTestManifest)
    }
}

private fun DependencyHandler.api(depName: String) = add("api", depName)
private fun DependencyHandler.implementation(depName: Any) = add("implementation", depName)
private fun DependencyHandler.kapt(depName: Any) = add("kapt", depName)
private fun DependencyHandler.compileOnly(depName: Any) = add("compileOnly", depName)
private fun DependencyHandler.debugApi(dependencyNotation: Any) = add("debugApi", dependencyNotation)
private fun DependencyHandler.debugImplementation(dependencyNotation: Any) = add("debugImplementation", dependencyNotation)
private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any) = add("androidTestImplementation", dependencyNotation)