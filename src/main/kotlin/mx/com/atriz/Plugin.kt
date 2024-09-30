package mx.com.atriz

import com.android.build.api.dsl.ApplicationExtension
import mx.com.atriz.core.Version
import org.gradle.api.Plugin
import org.gradle.api.Project

class Plugin : Plugin<Project> {

    override fun apply(target: Project) {
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

            flavorDimensions += "atriz"
            productFlavors {
                create("develop") {
                    dimension = "atriz"
                    buildConfigField("String", "API", "\"http://api.atriz.com.mx/\"")
                }

                create("GaleryStore") {
                    dimension = "atriz"
                    buildConfigField("String", "API", "\"http://api.atriz.com.mx/\"")
                }

                create("PlayStore") {
                    dimension = "atrizt"
                    buildConfigField("String", "API", "\"http://api.atriz.com.mx/\"")
                }
            }
        }
    }

    fun Project.application(): ApplicationExtension = extensions
        .getByType(ApplicationExtension::class.java)
}
