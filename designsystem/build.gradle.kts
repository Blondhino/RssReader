plugins {
    alias(libs.plugins.kmp.library.plugin)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.rssreader.designsystem"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            api(compose.preview)
            api(libs.androidx.activity.compose)
            api(libs.koin.android.compose)
        }
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material)
            api(compose.material3)
            api(compose.ui)
            api(compose.components.resources)
            api(compose.components.uiToolingPreview)
            api(libs.bundles.voyager)
        }
        nativeMain.dependencies { }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.rssreader.designsystem"
    generateResClass = auto
}

