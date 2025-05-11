import com.android.build.api.dsl.ApplicationExtension
import fi.joonasniemi.listcart.configureFirebase
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class FirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configureFirebase(this)
            }

            with(pluginManager) {
                apply("com.google.gms.google-services")
            }
        }
    }
}
