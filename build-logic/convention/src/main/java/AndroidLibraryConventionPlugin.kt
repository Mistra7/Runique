import com.android.build.api.dsl.LibraryExtension
import com.dzem.convention.ExtensionType
import com.dzem.convention.configureBuildTypes
import com.dzem.convention.configureKotlinAndroid
import com.dzem.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply(libs.findPlugin("android-library").get().get().pluginId)
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureBuildTypes(this, ExtensionType.LIBRARY)
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }
            dependencies {
                "testImplementation"(kotlin("test"))
            }

        }
    }
}