plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.runique.android.feature.ui)
}

android {
    namespace = "com.dzem.auth.presentation"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
}