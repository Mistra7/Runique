import com.dzem.convention.configureKotlinJvm
import com.dzem.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply(libs.findPlugin("jetbrains-kotlin-jvm").get().get().pluginId)
            configureKotlinJvm()
        }
    }
}