import fi.joonasniemi.myapplication.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidKoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", libs.findLibrary("koin.android").get())
                add("testImplementation", libs.findLibrary("koin.test.junit4").get())
            }
        }
    }
}