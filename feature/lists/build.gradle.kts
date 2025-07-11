plugins {
    alias(libs.plugins.listcart.android.library)
    alias(libs.plugins.listcart.android.library.compose)
    alias(libs.plugins.listcart.feature)
}

android {
    namespace = "fi.joonasniemi.listcart.feature.lists"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(libs.koin.androidx.compose)
    implementation(libs.napier)
}