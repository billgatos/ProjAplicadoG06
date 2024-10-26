plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //id("com.android.application")
    //id("org.jetbrains.kotlin.android")
    //id("com.google.devtools.ksp") // KSP plugin should be applied after Kotlin Android
    //id("com.google.devtools.ksp") version "1.8.21-1.0.11" // Use the latest KSP version
}




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
    val composeVersion = "1.5.1"

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
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material3:material3:1.3.0")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.activity:activity-compose:1.9.3")

    // Para o gerenciamento de estado e o uso de mutableStateOf
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    // adição do ksp
    //implementation("com.google.devtools.ksp:symbol-processing-api:1.8.21-1.0.11")
    //ksp("androidx.room:room-compiler:2.5.1")
}