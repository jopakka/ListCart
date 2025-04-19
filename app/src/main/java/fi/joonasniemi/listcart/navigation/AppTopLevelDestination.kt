package fi.joonasniemi.listcart.navigation

import kotlin.reflect.KClass

enum class AppTopLevelDestination(
    val route: KClass<*>,
) {
    AUTH(route = AuthNavigation::class),
    APP(route = ListCartAppNavigation::class),
}