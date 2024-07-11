plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.movix.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.movix.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(projects.shared)

    implementation(libs.bundles.compose.core)
    implementation(libs.ktor.android)
    implementation(libs.hilt.android)

    kapt(libs.hilt.compiler)

    debugImplementation(libs.compose.ui.tooling)
}