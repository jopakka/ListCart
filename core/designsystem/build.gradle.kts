plugins {
    alias(libs.plugins.myapplication.android.library)
    alias(libs.plugins.myapplication.android.library.compose)
}

android {
    namespace = "fi.joonasniemi.myapplication.core.designsystem"
}

dependencies {
    api(libs.androidx.material3)
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling)
    api(libs.androidx.ui.tooling.preview)
}