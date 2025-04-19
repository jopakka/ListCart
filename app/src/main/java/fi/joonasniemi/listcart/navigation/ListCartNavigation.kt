package fi.joonasniemi.listcart.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import fi.joonasniemi.listcart.feature.auth.AuthRoot
import fi.joonasniemi.listcart.feature.lists.ListsRoot
import kotlinx.serialization.Serializable

@Serializable
data object AuthNavigation

fun NavHostController.navigateToAuth(navOptions: NavOptions? = null) {
    navigate(AuthNavigation, navOptions)
}

fun NavGraphBuilder.authNavigation(
    onAuthenticated: () -> Unit,
) {
    composable<AuthNavigation> {
        AuthRoot(
            onAuthenticated = onAuthenticated,
        )
    }
}

@Serializable
data object ListCartAppNavigation

fun NavHostController.navigateToListCartApp(navOptions: NavOptions? = null) {
    navigate(ListCartAppNavigation, navOptions)
}

fun NavGraphBuilder.listCartAppNavigation(
) {
    composable<ListCartAppNavigation> {
        ListsRoot()
    }
}