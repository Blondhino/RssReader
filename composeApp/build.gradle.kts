import org.jetbrains.kotlin.config.JvmTarget
import versioning.Versioning

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.google.services)
    alias(libs.plugins.android.versioning)
}

sqldelight {
    databases {
        create("RssDatabase") {
            packageName = "com.biondic.rssreader"
        }
    }
}

kotlin {


    androidTarget {

    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.sqldelight.android.driver)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.koin.core)
            implementation(libs.bundles.arrow)
            implementation(libs.bundles.voyager)
            implementation(libs.ktor.client.core)
            implementation(libs.landscapist.coil3)
        }

        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqldelight.native.driver)
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget("21"))
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))


        android {
            namespace = "com.biondic.rssreader"
            compileSdk = libs.versions.android.compileSdk.get().toInt()

            defaultConfig {
                applicationId = "com.biondic.rssreader"
                minSdk = libs.versions.android.minSdk.get().toInt()
                targetSdk = libs.versions.android.targetSdk.get().toInt()
                versionCode = Versioning(project.rootDir.path).readVersion().versionCode
                versionName = Versioning(project.rootDir.path).readVersion().versionName
            }
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            signingConfigs {
                create("release") {
                    storeFile = project.rootProject.file("release/release.keystore")
                    keyAlias = "rssreader"
                    storePassword = System.getenv("ANDROID_RSS_READER_STORE_PASSWORD")
                    keyPassword = System.getenv("ANDROID_RSS_READER_KEY_PASSWORD")
                }
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = true
                    isDebuggable = false
                    signingConfig = signingConfigs.getByName("release")
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }
        }

        dependencies {
            debugImplementation(compose.uiTooling)
        }
    }
}

