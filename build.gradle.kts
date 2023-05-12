plugins {
    kotlin("android") version "1.8.10" apply false
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
}

buildscript {
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.hiltGradlePlugin)
    }
}

subprojects {
    plugins.applyBaseConfig(project)
}

fun com.android.build.gradle.BaseExtension.baseConfig() {
    compileSdkVersion(AndroidConfig.compileSdk)

    defaultConfig.apply {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

        vectorDrawables {
            useSupportLibrary = true
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
}

fun PluginContainer.applyBaseConfig(project: Project) {
    whenPluginAdded {
        when (this) {
            is com.android.build.gradle.AppPlugin -> {
                project.extensions
                    .getByType<com.android.build.gradle.AppExtension>()
                    .apply {
                        baseConfig()
                    }
            }
            is com.android.build.gradle.LibraryPlugin -> {
                project.extensions
                    .getByType<com.android.build.gradle.LibraryExtension>()
                    .apply {
                        baseConfig()
                    }
            }
        }
    }
}
