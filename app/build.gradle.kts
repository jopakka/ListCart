plugins {
    alias(libs.plugins.listcart.android.application)
    alias(libs.plugins.listcart.android.application.compose)
    alias(libs.plugins.listcart.android.koin)
    alias(libs.plugins.listcart.kotlinxSerialization)
}

android {
    namespace = "fi.joonasniemi.listcart"

    defaultConfig {
        applicationId = "fi.joonasniemi.listcart"
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

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.designsystem)
    implementation(projects.core.model)

    implementation(projects.feature.auth)
    implementation(projects.feature.lists)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.splashscreen)
    implementation(libs.napier)
}