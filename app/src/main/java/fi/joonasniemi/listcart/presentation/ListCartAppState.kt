package fi.joonasniemi.listcart.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import fi.joonasniemi.listcart.navigation.AppTopLevelDestination
import fi.joonasniemi.listcart.navigation.navigateToAuth
import fi.joonasniemi.listcart.navigation.navigateToListCartApp

@Composable
fun rememberListCartAppState(
    navController: NavHostController = rememberNavController(),
): ListCartAppState {
    return remember(
        navController,
    ) {
        ListCartAppState(
            navController = navController,
        )
    }
}

@Stable
class ListCartAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() {
            // Collect the currentBackStackEntryFlow as a state
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            // Fallback to previousDestination if currentEntry is null
            return currentEntry.value?.destination
        }

    val currentAppTopLevelDestination: AppTopLevelDestination?
        @Composable get() = AppTopLevelDestination.entries.firstOrNull { appTopLevelDestination ->
            currentDestination?.hasRoute(route = appTopLevelDestination.route) == true
        }

    fun navigateToAppTopLevelDestination(appTopLevelDestination: AppTopLevelDestination) {
        val navOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
                inclusive = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (appTopLevelDestination) {
            AppTopLevelDestination.AUTH -> navController.navigateToAuth(navOptions)
            AppTopLevelDestination.APP -> navController.navigateToListCartApp(navOptions)
        }
    }
}