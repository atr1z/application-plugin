import com.vanniktech.maven.publish.GradlePublishPlugin
import com.vanniktech.maven.publish.SonatypeHost

group = "mx.com.atriz"
version = "0.0.3"

plugins {
    kotlin("jvm") version "2.0.20"
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.2.1"
    id("com.vanniktech.maven.publish") version "0.28.0"
}

repositories {
    mavenCentral()
    mavenLocal()
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:8.6.0")
}

kotlin {
    jvmToolchain(17)
}

gradlePlugin {
    plugins {
        create("application") {
            id = "mx.com.atriz.application"
            implementationClass = "mx.com.atriz.application"
            version = version
            displayName = "Atriz Application Plugin"
            description = "All needed setup for application development"
        }
    }
}

mavenPublishing {
    configure(GradlePublishPlugin())
    pom {
        name.set("Application Plugin")
        description.set("Application settings ready to build")
        inceptionYear.set("2024")
        url.set("https://github.com/atr1z/application-plugin/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("atr1z")
                name.set("Jair M.")
                url.set("https://github.com/atr1z/")
            }
        }
        scm {
            url.set("https://github.com/AtrizDeveloper/Factory/")
            connection.set("scm:git:git://github.com/AtrizDeveloper/Factory.git")
            developerConnection.set("scm:git:ssh://git@github.com/AtrizDeveloper/Factory.git")
        }
    }
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}
