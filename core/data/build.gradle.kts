plugins {
    alias(libs.plugins.myapplication.android.library)
    alias(libs.plugins.myapplication.android.koin)
}

android {
    namespace = "fi.joonasniemi.myapplication.core.data"
}

dependencies {
    implementation(projects.core.model)
}