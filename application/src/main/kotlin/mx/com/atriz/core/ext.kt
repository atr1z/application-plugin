package mx.com.atriz.core

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

fun Project.application(): ApplicationExtension = extensions
    .getByType(ApplicationExtension::class.java)

fun Project.library(): LibraryExtension = extensions.getByType(LibraryExtension::class.java)