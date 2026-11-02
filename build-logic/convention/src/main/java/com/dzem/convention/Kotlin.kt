package com.dzem.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.named
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.internal.name.JvmStandardClassIds
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension
) {
    commonExtension.apply {
        compileSdk = libs.findVersion("projectCompileSdkVersion").get().toString().toInt()
        defaultConfig.minSdk = libs.findVersion("projectMinSdkVersion").get().toString().toInt()
        compileOptions.apply {
            isCoreLibraryDesugaringEnabled = true
            sourceCompatibility = JavaVersion.VERSION_25
            targetCompatibility = JavaVersion.VERSION_25
        }
    }

    dependencies {
        "coreLibraryDesugaring"(libs.findLibrary("desugar.jdk.libs").get())
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_25
        targetCompatibility = JavaVersion.VERSION_25
    }

    configureKotlin()
}

private fun Project.configureKotlin() {
    tasks.named<KotlinJvmCompile>("compileKotlin") {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_25)
        }
    }
}

