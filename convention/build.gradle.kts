import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "com.rssreader.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

gradlePlugin {
    plugins {
        register("versioningPlugin") {
            id = "rssreader.android.versioning"
            implementationClass = "versioning.VersioningPlugin"
        }
    }
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}