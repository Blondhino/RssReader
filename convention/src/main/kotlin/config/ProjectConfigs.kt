package config

import model.AndroidSdkConfiguration
import model.JavaConfiguration
import org.gradle.api.JavaVersion

object ProjectConfigs {
    val namespace = "com.biondic.rssreader"
    val databaseName = "RssDatabase"

    val androidSdkConfig = AndroidSdkConfiguration(
        minSdk = 24,
        targetSdk = 34,
        compileSdk = 35,
    )
    val javaConfig = JavaConfiguration(
        sourceCompatibility = JavaVersion.VERSION_21,
        targetCompatibility = JavaVersion.VERSION_21,
    )
}