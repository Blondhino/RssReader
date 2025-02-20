import config.ProjectConfigs

plugins {
    id("kmp-library-plugin")
    alias(libs.plugins.sqldelight)
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
        }
        nativeMain.dependencies {
            api(libs.ktor.client.darwin)
            api(libs.sqldelight.native.driver)
        }
    }
}
