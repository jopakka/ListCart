plugins {
    alias(libs.plugins.listcart.android.application)
    alias(libs.plugins.listcart.android.application.compose)
    alias(libs.plugins.listcart.android.koin)
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
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.designsystem)
    implementation(projects.core.model)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
}