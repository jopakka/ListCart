package fi.joonasniemi.listcart.feature.auth

import androidx.compose.foundation.text.input.TextFieldState

data class AuthState(
    val email: TextFieldState = TextFieldState(),
    val hasEmailError: Boolean = false,
    val password: TextFieldState = TextFieldState(),
    val hasPasswordError: Boolean = false,
    val isValid: Boolean = false,
    val isLoading: Boolean = false,
    val error: AuthError? = null,
    val isLoginSuccess: Boolean = false,
)
