plugins {
    alias(libs.plugins.runique.jvm.library)
}

dependencies {
    implementation(project(":core:domain"))
}
