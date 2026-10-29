plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.dzem.core.datebase"
}

dependencies {
    implementation(libs.org.mongodb.bson)

    implementation(projects.core.domain)
}