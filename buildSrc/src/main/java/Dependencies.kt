import org.gradle.api.artifacts.dsl.DependencyHandler

const val kotlinVersion = "1.8.10"

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
        const val hilt = "2.44"
        const val navigationYoyager = "1.0.0-rc02"
        const val room = "2.4.3"
        const val dataStore = "1.0.0"
        const val lifecycle = "2.5.1"
        const val retrofit = "2.9.0"
        const val okhttp3 = "4.10.0"
        const val logging_interceptor = "4.10.0"
        const val chucker = "3.5.2"
        const val firebaseBom = "26.1.1"
        const val firebaseServicesAuth = "20.5.0"

        //compose
        const val compose = "1.4.3"
        const val composeViewModel = "2.5.1"
        const val composeNavigation = "2.6.0-alpha04"
        const val composeLiveData = "1.4.0-alpha03"
        const val composeHilt = "1.1.0-alpha01"
        const val composeLifecycle = "2.6.0-alpha04"
        const val coilCompose = "2.2.2"
        const val composeConstraint = "1.0.1"
        const val accompanist = "0.27.0"
        const val composeActivity = "1.6.1"
        const val composeMaterial = "1.3.1"
        const val composeMaterial3 = "1.1.0-alpha03"
    }

    //Kotlin
    private const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}"
    private const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    private const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    //Compose
    private const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    private const val composefoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    private const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    private const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    private const val composeMaterial = "androidx.compose.material:material:${Versions.composeMaterial}"
    private const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    private const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    private const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    private const val composeViewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
    private const val composeMaterialIcons = "androidx.compose.material:material-icons-extended:${Versions.composeMaterial}"
    private const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    private const val composeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.composeLiveData}"
    private const val composeHilt = "androidx.hilt:hilt-navigation-compose:${Versions.composeHilt}"
    private const val composeLifecycle = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.composeLifecycle}"
    private const val accompanistUicontroller = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    private const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"
    const val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
    const val accompanistIndicators = "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"
    const val accompanistPermissions = "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"

    //Hilt
    private const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    private const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    //Navigation
    private const val voyagerNavigator = "cafe.adriel.voyager:voyager-navigator:${Versions.navigationYoyager}"
    private const val voyagerNavigatorBottomSheet =
        "cafe.adriel.voyager:voyager-bottom-sheet-navigator:${Versions.navigationYoyager}"
    private const val voyagerNavigatorTabNavigator =
        "cafe.adriel.voyager:voyager-tab-navigator:${Versions.navigationYoyager}"
    private const val voyagerNavigatorTransitions =
        "cafe.adriel.voyager:voyager-transitions:${Versions.navigationYoyager}"
    private const val voyagerNavigatorViewModel = "cafe.adriel.voyager:voyager-androidx:${Versions.navigationYoyager}"
    private const val voyagerNavigatorHilt = "cafe.adriel.voyager:voyager-hilt:${Versions.navigationYoyager}"
    private const val voyagerNavigatorLivedata = "cafe.adriel.voyager:voyager-livedata:${Versions.navigationYoyager}"

    //Room
    private const val room = "androidx.room:room-runtime:${Versions.room}"
    private const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    private const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val datastorePreferences = "androidx.datastore:datastore-preferences:${Versions.dataStore}"

    //lifecycle
    private const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:${Versions.lifecycle}"
    private const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"
    private const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    //NetWork
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
    const val chucker = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val chuckerNoOp = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"

    //FireBase
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx"
    const val firebaseServicesAuth = "com.google.android.gms:play-services-auth:${Versions.firebaseServicesAuth}"

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
        implementation(composeMaterial3)
        implementation(composeViewmodel)
        implementation(composeMaterialIcons)
        implementation(composeNavigation)
        implementation(composeLivedata)
        implementation(composeHilt)
        implementation(composeLifecycle)
        implementation(accompanistUicontroller)
        implementation(coilCompose)
        debugImplementation(composeTooling)
        debugImplementation(composeTestManifest)
    }

    fun DependencyHandler.addHilt() {
        implementation(hilt)
        kapt(hiltCompiler)
    }

    fun DependencyHandler.addNavigation() {
        implementation(voyagerNavigator)
        implementation(voyagerNavigatorBottomSheet)
        implementation(voyagerNavigatorTabNavigator)
        implementation(voyagerNavigatorTransitions)
        implementation(voyagerNavigatorViewModel)
        implementation(voyagerNavigatorHilt)
        implementation(voyagerNavigatorLivedata)
    }

    fun DependencyHandler.addRoom() {
        implementation(room)
        implementation(datastorePreferences)
        implementation(roomKtx)
        kapt(roomCompiler)
    }

    fun DependencyHandler.addLifecycle() {
        implementation(lifecycleCommon)
        implementation(lifecycleProcess)
        kapt(lifecycleRuntime)
    }

    fun DependencyHandler.addNetworkDependencies() {
        implementation(retrofit)
        implementation(retrofitConverterGson)
        implementation(okhttp3)
        implementation(logging_interceptor)
        debugImplementation(chucker)
        releaseImplementation(chuckerNoOp)
    }

    fun DependencyHandler.addFirebaseBom(){
        implementation(platform(firebaseBom))
        implementation(firebaseAuth)
    }
}

private fun DependencyHandler.api(depName: String) = add("api", depName)
private fun DependencyHandler.implementation(depName: Any) = add("implementation", depName)
private fun DependencyHandler.kapt(depName: Any) = add("kapt", depName)
private fun DependencyHandler.compileOnly(depName: Any) = add("compileOnly", depName)
private fun DependencyHandler.debugApi(dependencyNotation: Any) = add("debugApi", dependencyNotation)
private fun DependencyHandler.debugImplementation(dependencyNotation: Any) =
    add("debugImplementation", dependencyNotation)
private fun DependencyHandler.releaseImplementation(dependencyNotation: Any) =
    add("releaseImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any) =
    add("androidTestImplementation", dependencyNotation)