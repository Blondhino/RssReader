import config.ProjectConfigs
import ext.configureCompileOptions
import ext.configureDefaultConfig
import ext.configurePlatformTargets

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
}

android {
    compileSdk = ProjectConfigs.androidSdkConfig.compileSdk
    configureDefaultConfig()
    configureCompileOptions()
}

kotlin {
    configurePlatformTargets()
}
