plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    //id("com.android.application")
    //id("org.jetbrains.kotlin.android")
    //id("com.google.devtools.ksp") version "1.8.0-1.0.9"
}

//apply plugin: "com.google.devtools.ksp"


android {
    namespace = "com.example.lojasocialbd"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lojasocialbd"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val composeVersion = "1.7.5"

    implementation(kotlin("script-runtime"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dependência para ViewModel com Jetpack Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Jetpack Compose
    implementation(libs.ui)
    implementation(libs.material3)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.activity.compose.v193)

    // Para o gerenciamento de estado e o uso de mutableStateOf
    implementation(libs.androidx.runtime)

    // adição do ksp
    val room_version = "2.6.1" // Check for the latest version

    implementation(libs.androidx.room.runtime)
    //kapt("androidx.room:room-compiler:$room_version") // Use kapt for Kotlin
    //ksp("androidx.room:room-compiler:2.5.0") // Use ksp ao invés de kapt
    implementation(libs.androidx.room.ktx) // Optional - Kotlin Extensions and Coroutines support for Room

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0") // or any latest stable version
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0") // or any latest stable version


}