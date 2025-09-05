plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.volunteerpanel"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.volunteerpanel"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.intuit.sdp:sdp-android:1.1.1")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
}