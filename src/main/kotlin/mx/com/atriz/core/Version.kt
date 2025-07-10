package mx.com.atriz.core

import org.gradle.api.JavaVersion

object Version {
    const val COMPILE_SDK = 36
    const val MIN_SDK = 30
    const val TARGET_SDK = 36

    fun java(): JavaVersion = JavaVersion.VERSION_21
}