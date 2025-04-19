plugins {
    alias(libs.plugins.listcart.android.library)
    alias(libs.plugins.listcart.android.library.compose)
    alias(libs.plugins.listcart.android.koin)
    alias(libs.plugins.listcart.android.koin.compose)
    alias(libs.plugins.listcart.feature)
}

android {
    namespace = "fi.joonasniemi.listcart.feature.auth"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.data)
    implementation(libs.napier)
}