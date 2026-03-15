package mx.com.atriz

import com.android.build.api.dsl.ApplicationExtension
import mx.com.atriz.core.Version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class Application : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply {
            plugin("com.android.application")
            plugin("kotlin-android")
            plugin("org.jetbrains.kotlin.plugin.compose")
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
                    isDebuggable = false
                    isJniDebuggable = false
                    isShrinkResources = false
                    multiDexEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }

                debug {
                    isMinifyEnabled = false
                    isJniDebuggable = true
                    isDebuggable = true
                    isShrinkResources = false
                    multiDexEnabled = true
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

        target.tasks.withType(KotlinCompile::class.java) {
            compilerOptions {
                jvmTarget.set(Version.jvmTarget)
            }
        }

        target.dependencies.apply {
            add("implementation", platform("androidx.compose:compose-bom:${Version.COMPOSE_BOM}"))
            add("implementation", "androidx.compose.ui:ui")
            add("implementation", "androidx.compose.ui:ui-graphics")
            add("implementation", "androidx.compose.ui:ui-tooling-preview")
            add("implementation", "androidx.compose.material3:material3")
            add("implementation", "androidx.activity:activity-compose")
            add("debugImplementation", "androidx.compose.ui:ui-tooling")
            add("debugImplementation", "androidx.compose.ui:ui-test-manifest")
        }
    }

    fun Project.application(): ApplicationExtension = extensions
        .getByType(ApplicationExtension::class.java)
}
