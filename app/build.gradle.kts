plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.bilyoner.bets"
    compileSdk = 35

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.bilyoner.bets"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField ("String", "API_KEY", "\"5a0c5a5f3d36a3e8a5a740506bb1cf4b\"")
        buildConfigField ("String", "BASE_URL", "\"https://api.the-odds-api.com\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField ("String", "API_KEY", "\"5a0c5a5f3d36a3e8a5a740506bb1cf4b\"")
            buildConfigField ("String", "BASE_URL", "\"https://api.the-odds-api.com\"")
        }
        debug {
            buildConfigField ("String", "API_KEY", "\"5a0c5a5f3d36a3e8a5a740506bb1cf4b\"")
            buildConfigField ("String", "BASE_URL", "\"https://api.the-odds-api.com\"")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

configurations.all {
    resolutionStrategy {
        force("com.squareup:javapoet:1.13.0")
    }
}

dependencies {
    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Lifecycle
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.runtime)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Firebase Analytics
    implementation(libs.firebase.analytics)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // JavaPoet sabitleme
    implementation(libs.javapoet)

    //LiveData
    implementation(libs.lifecycle.livedata.ktx)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
