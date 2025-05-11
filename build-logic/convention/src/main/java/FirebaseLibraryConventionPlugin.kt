import com.android.build.gradle.LibraryExtension
import fi.joonasniemi.listcart.configureFirebase
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class FirebaseLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                configureFirebase(this)
            }
        }
    }
}
