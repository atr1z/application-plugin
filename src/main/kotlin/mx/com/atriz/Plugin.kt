package mx.com.atriz

import com.android.build.api.dsl.ApplicationExtension
import mx.com.atriz.core.Version
import org.gradle.api.Plugin
import org.gradle.api.Project

class Application : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply {
            plugin("com.android.application")
            plugin("kotlin-android")
        }
        target.application().apply {
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
                compose = true
            }

            buildTypes {
                release {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            compileOptions {
                sourceCompatibility = Version.java()
                targetCompatibility = Version.java()
            }
        }
    }

    fun Project.application(): ApplicationExtension = extensions
        .getByType(ApplicationExtension::class.java)
}
