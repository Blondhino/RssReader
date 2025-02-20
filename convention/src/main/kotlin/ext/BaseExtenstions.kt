package ext

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import config.ProjectConfigs
import java.io.File

internal fun BaseExtension.configureDefaultConfig() {
    defaultConfig {
        minSdk = ProjectConfigs.androidSdkConfig.minSdk
        targetSdk = ProjectConfigs.androidSdkConfig.targetSdk
        versionCode = 1
        versionName = "1.0.0"
    }
}

internal fun BaseExtension.configureCompileOptions() {
    compileOptions {
        sourceCompatibility = ProjectConfigs.javaConfig.sourceCompatibility
        targetCompatibility = ProjectConfigs.javaConfig.targetCompatibility
    }
}

internal fun BaseExtension.configureSigning() {
    signingConfigs {
        create("release") {
            storeFile = File("../../../../../release/release.keystore")
            keyAlias = "rssreader"
            storePassword = System.getenv("ANDROID_RSS_READER_STORE_PASSWORD")
            keyPassword = System.getenv("ANDROID_RSS_READER_KEY_PASSWORD")
        }
    }
}

internal fun BaseAppModuleExtension.configureBuildTypes() {
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("release")
        }
    }
}