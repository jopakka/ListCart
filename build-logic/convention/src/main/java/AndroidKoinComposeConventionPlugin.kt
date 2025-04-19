import fi.joonasniemi.listcart.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidKoinComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", libs.findLibrary("koin-android").get())
                add("implementation", libs.findLibrary("koin-androidx-compose").get())
                add("implementation", libs.findLibrary("koin-androidx-compose-navigation").get())
                add("testImplementation", libs.findLibrary("koin-test-junit4").get())
            }
        }
    }
}