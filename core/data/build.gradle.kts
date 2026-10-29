plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.dzem.core.data"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.core.datebase)
}