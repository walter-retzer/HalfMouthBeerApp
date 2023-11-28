plugins {
    id("com.android.application")
    id("org.jetbrains.compose")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.gms.google-services")
    kotlin("android")
}

android {
    namespace = "app.halfmouth.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "app.halfmouth.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))

    //Compose Libs
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui-tooling:1.5.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation("androidx.compose.foundation:foundation:1.5.4")
    implementation("androidx.compose.material:material:1.4.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    debugImplementation(libs.compose.ui.tooling)

    //LifeCycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    //Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    //Ktor Libs
    implementation("io.ktor:ktor-client-core:1.6.3")
    implementation("io.ktor:ktor-client-android:1.6.3")
    implementation("io.ktor:ktor-client-serialization:1.6.3")
    implementation("io.ktor:ktor-client-logging:1.6.3")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    //Charts Lib
    implementation("com.github.tehras:charts:0.2.4-alpha")

    //Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.28.0")

    //Firebase Authentication
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    //Coil-Compose (for loading images remotely)
    implementation("io.coil-kt:coil-compose:2.4.0")
}