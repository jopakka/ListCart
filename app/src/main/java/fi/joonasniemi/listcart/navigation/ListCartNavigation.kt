package fi.joonasniemi.listcart.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import fi.joonasniemi.listcart.core.model.data.ShoppingList
import fi.joonasniemi.listcart.feature.auth.AuthRoot
import fi.joonasniemi.listcart.feature.lists.ListsRoot
import fi.joonasniemi.listcart.feature.listview.ListInfo
import fi.joonasniemi.listcart.feature.listview.ListViewRoot
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
    onNavigateToList: (ShoppingList) -> Unit,
) {
    composable<ListCartAppNavigation> {
        ListsRoot(
            onNavigateToList = onNavigateToList,
        )
    }
}

fun NavHostController.navigateToListView(info: ListInfo, navOptions: NavOptions? = null) {
    navigate(info, navOptions)
}

fun NavGraphBuilder.listViewNavigation() {
    composable<ListInfo> {
        ListViewRoot()
    }
}