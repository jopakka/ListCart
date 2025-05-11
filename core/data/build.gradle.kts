plugins {
    alias(libs.plugins.listcart.android.library)
    alias(libs.plugins.listcart.android.koin)
    alias(libs.plugins.listcart.firebase.library)
    alias(libs.plugins.listcart.kotlinxSerialization)
}

android {
    namespace = "fi.joonasniemi.listcart.core.data"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.napier)
}