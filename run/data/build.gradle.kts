plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.dzem.run.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.google.android.gms.play.services.location)
    implementation(libs.androidx.work)
    implementation(libs.koin.android.workmanager)
    implementation(libs.kotlinx.serialization.json)

    implementation(projects.core.domain)
    implementation(projects.core.datebase)
    implementation(projects.run.domain)
}