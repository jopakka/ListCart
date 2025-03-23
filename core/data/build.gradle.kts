plugins {
    alias(libs.plugins.listcart.android.library)
    alias(libs.plugins.listcart.android.koin)
}

android {
    namespace = "fi.joonasniemi.listcart.core.data"
}

dependencies {
    implementation(projects.core.model)
}