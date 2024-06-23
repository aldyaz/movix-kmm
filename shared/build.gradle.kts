import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version libs.versions.kotlin
    alias(libs.plugins.buildConfig)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.ktor.common)
        }
        androidMain.dependencies {
            implementation(libs.ktor.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.ios)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }

    task("testClasses")
}

buildConfig {
    val props = loadPropertiesFile("local.properties")

    packageName("com.movix.shared")
    useKotlinOutput()

    buildConfigField("String", "API_KEY", "\"${props["API_KEY"]}\"")
}

android {
    namespace = "com.movix.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

fun loadPropertiesFile(file: String): Map<*, *> {
    val configPath = "../$file"
    val props: Map<*, *> = if (file(configPath).exists()) {
        Properties().apply {
            load(FileInputStream(file(configPath)))
        }
    } else {
        project.properties
    }
    return props
}
