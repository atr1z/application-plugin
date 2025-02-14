plugins {
    signing
    kotlin("jvm") version "2.0.21"
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.3.1"
}

group = "mx.com.atriz"
version = "0.0.1"

repositories {
    mavenCentral()
    mavenLocal()
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:8.8.0")
}

kotlin {
    jvmToolchain(21)
}


gradlePlugin {
    website = "https://atriz.com.mx"
    vcsUrl = "https://github.com/atr1z/application-plugin"
    plugins {
        create("application") {
            id = "mx.com.atriz.application"
            implementationClass = "mx.com.atriz.Application"
            displayName = "Application module Plugin"
            version = project.version
            description = "This plugin is used to create a new module in an Android project with the necessary gradle configurations."
            tags = listOf("atriz", "android-plugin", "android")
        }
    }
}

signing {
    useInMemoryPgpKeys(
        System.getenv("SIGNING_KEY") ?: "",
        System.getenv("SIGNING_PASSWORD") ?: ""
    )
    sign(configurations.runtimeElements.get())
}