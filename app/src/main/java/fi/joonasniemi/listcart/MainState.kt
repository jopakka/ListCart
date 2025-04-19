package fi.joonasniemi.listcart

sealed class MainState {
    data object Loading : MainState()
    data object NotAuthenticated : MainState()
    data object Authenticated : MainState()
}