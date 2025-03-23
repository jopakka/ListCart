plugins {
    alias(libs.plugins.listcart.android.library)
    alias(libs.plugins.listcart.android.library.compose)
}

android {
    namespace = "fi.joonasniemi.listcart.core.designsystem"
}

dependencies {
    api(libs.androidx.material3)
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling)
    api(libs.androidx.ui.tooling.preview)
}