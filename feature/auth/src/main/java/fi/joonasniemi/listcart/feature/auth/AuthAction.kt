package fi.joonasniemi.listcart.feature.auth

sealed interface AuthAction {
    data object LoginClicked : AuthAction
}