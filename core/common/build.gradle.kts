plugins {
    alias(libs.plugins.listcart.android.library)
    alias(libs.plugins.listcart.android.library.compose)
}

android {
    namespace = "fi.joonasniemi.listcart.core.common"
}

dependencies {
    implementation(libs.androidx.activity.compose)
}