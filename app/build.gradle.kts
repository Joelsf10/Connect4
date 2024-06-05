import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 34
    namespace="com.example.connect4"

    defaultConfig {
        applicationId = "com.example.connect4"
        minSdk = 21
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5" // Asegúrate de usar la versión correcta de Compose Compiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.4.2")
    implementation("androidx.databinding:adapters:3.2.0-alpha11")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui:1.4.2")
    implementation("androidx.compose.material:material:1.4.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.2")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.2")
    implementation("androidx.navigation:navigation-compose:2.6.0")

    // Room components
    implementation("androidx.room:room-ktx:2.5.1")
    androidTestImplementation("androidx.room:room-testing:2.5.1")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.1")

    // Kotlin components
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // UI
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.7.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") {
        exclude(group = "com.android.support", module = "support-annotations")
    }
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}





