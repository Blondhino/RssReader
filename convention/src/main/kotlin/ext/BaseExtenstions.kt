package ext

import com.android.build.gradle.BaseExtension
import config.ProjectConfigs

internal fun BaseExtension.configureDefaultConfig() {
    defaultConfig {
        minSdk = ProjectConfigs.androidSdkConfig.minSdk
    }
}

internal fun BaseExtension.configureCompileOptions(){
    compileOptions {
        sourceCompatibility = ProjectConfigs.javaConfig.sourceCompatibility
        targetCompatibility = ProjectConfigs.javaConfig.targetCompatibility
    }
}