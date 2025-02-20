plugins {
    id("kmp-library-plugin")
}

android {
    namespace = "com.biondic.core"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            api(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            api(libs.bundles.arrow)
            api(libs.ktor.client.core)
        }
        nativeMain.dependencies {
            api(libs.ktor.client.darwin)
        }
    }
}
