package mx.com.atriz.core

import org.gradle.api.JavaVersion

object Version {
    const val COMPILE_SDK = 34
    const val MIN_SDK = 30
    const val TARGET_SDK = 34

    fun java(): JavaVersion = JavaVersion.VERSION_17
}