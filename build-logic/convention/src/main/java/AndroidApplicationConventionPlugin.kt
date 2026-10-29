import com.android.build.api.dsl.ApplicationExtension
import com.dzem.convention.ExtensionType
import com.dzem.convention.configureBuildTypes
import com.dzem.convention.configureKotlinAndroid
import com.dzem.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply(libs.findPlugin("android-application").get().get().pluginId)
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }

                configureKotlinAndroid(this)
                configureBuildTypes(this, ExtensionType.APPLICATION)
            }
        }
    }
}