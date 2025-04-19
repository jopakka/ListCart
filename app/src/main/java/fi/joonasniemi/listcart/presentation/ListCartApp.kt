package fi.joonasniemi.listcart.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import fi.joonasniemi.listcart.MainState
import fi.joonasniemi.listcart.navigation.AppTopLevelDestination
import fi.joonasniemi.listcart.navigation.authNavigation
import fi.joonasniemi.listcart.navigation.listCartAppNavigation
import io.github.aakira.napier.Napier

@Composable
fun ListCartApp(
    mainState: MainState,
    listCartAppState: ListCartAppState = rememberListCartAppState(),
    startDestination: Any = AppLoadingNavigation,
) {
    LaunchedEffect(mainState) {
        when (mainState) {
            MainState.Loading -> Unit
            MainState.Authenticated -> listCartAppState.navigateToAppTopLevelDestination(
                AppTopLevelDestination.APP
            )

            MainState.NotAuthenticated -> listCartAppState.navigateToAppTopLevelDestination(
                AppTopLevelDestination.AUTH
            )
        }
    }

    NavHost(
        navController = listCartAppState.navController,
        modifier = Modifier.fillMaxSize(),
        startDestination = startDestination,
    ) {
        appLoading()

        authNavigation(
            onAuthenticated = {
                listCartAppState.navigateToAppTopLevelDestination(
                    AppTopLevelDestination.APP
                )
            }
        )

        listCartAppNavigation()
    }
}