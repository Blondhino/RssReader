plugins {
    alias(libs.plugins.kmp.library.plugin)
}

android {
    namespace = "com.rssreader.article"
}

kotlin {

    sourceSets {
        androidMain.dependencies {

        }
        commonMain.dependencies {


        }
        nativeMain.dependencies {
        }

    }
}