plugins {
    alias(libs.plugins.runique.android.library)
    alias(libs.plugins.runique.android.room)
    alias(libs.plugins.runique.jvm.ktor)
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
    implementation(libs.bundles.koin)
}