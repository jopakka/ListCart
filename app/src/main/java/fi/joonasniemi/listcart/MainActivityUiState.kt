package fi.joonasniemi.listcart

import fi.joonasniemi.listcart.core.model.data.UserConfig

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState

    data class Success(val userConfig: UserConfig) : MainActivityUiState

    fun shouldKeepSplashScreen(): Boolean = this is Loading

    fun shouldUseDarkTheme(isSystemDarkTheme: Boolean) = isSystemDarkTheme
}