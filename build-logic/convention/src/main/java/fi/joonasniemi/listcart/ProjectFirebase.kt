package fi.joonasniemi.listcart

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate
import kotlin.apply

internal fun Project.configureFirebase(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        dependencies {
            val bom = libs.findLibrary("firebase-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("firebase-auth").get())
            add("implementation", libs.findLibrary("firebase-firestore").get())
        }
    }
}