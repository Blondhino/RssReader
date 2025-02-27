plugins {
    alias(libs.plugins.kmp.app.plugin)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.serialization.plugin)
}

kotlin {

    sourceSets {

        androidMain.dependencies {
            implementation(libs.koin.android.compose)
        }
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.designsystem)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.landscapist.coil3)
        }
        nativeMain.dependencies {}
    }
}

