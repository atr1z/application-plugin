package mx.com.atriz.core

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Version {
    const val COMPILE_SDK = 36
    const val MIN_SDK = 30
    const val TARGET_SDK = 36
    const val COMPOSE_BOM = "2025.01.01"

    fun java(): JavaVersion = JavaVersion.VERSION_21
    val jvmTarget = JvmTarget.JVM_21
}