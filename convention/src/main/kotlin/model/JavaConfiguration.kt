package model

import org.gradle.api.JavaVersion

data class JavaConfiguration(
    val sourceCompatibility: JavaVersion,
    val targetCompatibility: JavaVersion,
)
