import com.dzem.convention.addUiLayerDependencies
import com.dzem.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureUiConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply(libs.findPlugin("runique-android-library-compose").get().get().pluginId)

            dependencies {
                addUiLayerDependencies(target)
            }
        }
    }
}