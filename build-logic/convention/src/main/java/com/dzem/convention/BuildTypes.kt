package com.dzem.convention

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.invoke

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension,
    extensionType: ExtensionType
) {
    commonExtension.apply {
        buildFeatures.buildConfig = true

        val apiKey = gradleLocalProperties(rootDir, project.providers).getProperty("API_KEY")

        buildTypes {
            getByName("debug") {
                configureDebugBuildType(apiKey)
            }

            getByName("release") {
                configureReleaseBuildType(apiKey)
                if(extensionType == ExtensionType.APPLICATION) {
                    (this as ApplicationBuildType).configureReleaseOptimization()
                }
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(apiKey: String) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")
}

private fun BuildType.configureReleaseBuildType(apiKey: String) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")
}

private fun ApplicationBuildType.configureReleaseOptimization() {
    isMinifyEnabled = true
    optimization {
        enable = true
        packageScope = setOf(
            "androidx.**",
            "kotlin.**",
            "kotlinx.**"
        )
    }
}