import config.ProjectConfigs
import ext.configureBuildTypes
import ext.configureCompileOptions
import ext.configureDefaultConfig
import ext.configurePlatformTargets
import ext.configureSigning
import gradle.kotlin.dsl.accessors._b2937d1b40dda98f7678619569c6e850.kotlin

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.multiplatform")
}

android {
    compileSdk = ProjectConfigs.androidSdkConfig.compileSdk
    namespace = ProjectConfigs.namespace

    defaultConfig {
        applicationId = ProjectConfigs.namespace
    }
    configureDefaultConfig()
    configureCompileOptions()
    configureSigning()
    configureBuildTypes()
}

kotlin {
    configurePlatformTargets()
}