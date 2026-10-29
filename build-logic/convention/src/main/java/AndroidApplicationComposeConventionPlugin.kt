import com.android.build.api.dsl.ApplicationExtension
import com.dzem.convention.configureAndroidCompose
import com.dzem.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply(libs.findPlugin("runique-android-application").get().get().pluginId)
            pluginManager.apply(libs.findPlugin("kotlin-compose").get().get().pluginId)
            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }
}