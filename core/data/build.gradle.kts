plugins {
    alias(libs.plugins.listcart.android.library)
    alias(libs.plugins.listcart.android.koin)
    alias(libs.plugins.google.services)
}

android {
    namespace = "fi.joonasniemi.listcart.core.data"
}

dependencies {
    implementation(projects.core.model)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.napier)
}