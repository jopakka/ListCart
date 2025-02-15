plugins {
    alias(libs.plugins.myapplication.android.application)
    alias(libs.plugins.myapplication.android.application.compose)
    alias(libs.plugins.myapplication.android.koin)
}

android {
    namespace = "fi.joonasniemi.myapplication"

    defaultConfig {
        applicationId = "fi.joonasniemi.myapplication"
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
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.designsystem)
    implementation(projects.core.model)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
}