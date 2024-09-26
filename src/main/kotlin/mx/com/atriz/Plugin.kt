package mx.com.atriz

import mx.com.atriz.config.Configuration
import mx.com.atriz.config.Types.*
import mx.com.atriz.core.Version
import mx.com.atriz.core.application
import mx.com.atriz.core.library
import org.gradle.api.Plugin
import org.gradle.api.Project


class Plugin : Plugin<Project> {

    override fun apply(target: Project) {
        val config = target.extensions.create("atriz", Configuration::class.java)
        target.apply {
            plugin("kotlin-android")
            when (config.moduleType) {
                App -> {
                    plugin("com.android.application")
                    application(target, config)
                }

                Library -> {
                    plugin("com.android.library")
                    library(target, config)
                }
            }
        }

    }


    private fun application(project: Project, config: Configuration) {
        project.application().apply {
            compileSdk = Version.COMPILE_SDK

            defaultConfig {
                minSdk = Version.MIN_SDK
                targetSdk = Version.TARGET_SDK
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                multiDexEnabled = true
            }

            buildFeatures {
                buildConfig = true
                viewBinding = true
                compose = config.isComposeEnabled
            }
        }
    }

    private fun library(project: Project, config: Configuration) {
        project.library().apply {
            compileSdk = Version.COMPILE_SDK
            defaultConfig {
                minSdk = Version.MIN_SDK
            }
            buildFeatures {
                buildConfig = true
                viewBinding = true
                compose = config.isComposeEnabled
            }
        }
    }
}
