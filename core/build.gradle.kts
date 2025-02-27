import config.ProjectConfigs

plugins {
    alias(libs.plugins.kmp.library.plugin)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.serialization.plugin)
}

sqldelight {
    databases {
        create(ProjectConfigs.databaseName) {
            packageName = ProjectConfigs.namespace
        }
    }
}

android {
    namespace = "com.biondic.core"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            api(libs.ktor.client.okhttp)
            api(libs.sqldelight.android.driver)
        }
        commonMain.dependencies {
            api(libs.bundles.arrow)
            api(libs.ktor.client.core)
            api(libs.serialization.json)
            api(libs.navigation.compose)
            api(libs.koin.compose.viewmodel)
            implementation(libs.koin.core)
        }
        nativeMain.dependencies {
            api(libs.ktor.client.darwin)
            api(libs.sqldelight.native.driver)
        }
    }
}
