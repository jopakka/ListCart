package fi.joonasniemi.listcart.feature.auth

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.joonasniemi.listcart.core.data.auth.AuthRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class AuthViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AuthState()
        )


    fun onAction(action: AuthAction) {
        when (action) {
            is AuthAction.LoginClicked -> login()
        }
    }

    suspend fun run() {
        withContext(coroutineContext) {
            launch { collectEmail() }
            launch { collectPassword() }
        }
    }

    private suspend fun collectEmail() =
        snapshotFlow { _state.value.email.text }.collectLatest { emailText ->
            val hasErrors = checkEmailErrors(emailText.trim().toString())
            val isEmpty = emailText.isEmpty()
            _state.update {
                it.copy(
                    hasEmailError = !isEmpty && hasErrors,
                    isValid = !it.hasPasswordError && !it.password.text.isEmpty() && !isEmpty && !hasErrors,
                )
            }
        }

    private suspend fun collectPassword() =
        snapshotFlow { _state.value.password.text }.collectLatest { passwordText ->
            val hasErrors = checkPasswordErrors(passwordText.toString())
            val isEmpty = passwordText.isEmpty()
            _state.update {
                it.copy(
                    hasPasswordError = !isEmpty && hasErrors,
                    isValid = !it.hasEmailError && !it.email.text.isEmpty() && !isEmpty && !hasErrors,
                )
            }
        }

    private fun login() {
        val email = state.value.email.text.trim().toString()
        val password = state.value.password.text.toString()
        val hasEmailError = checkEmailErrors(email)
        val hasPasswordErrors = checkPasswordErrors(password)
        if (hasEmailError || hasPasswordErrors) return
        _state.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                authRepository.login(email, password)
                _state.update { it.copy(isLoginSuccess = true) }
            } catch (e: Exception) {
                Napier.e("Login exception: ${e.message}")
                _state.update { it.copy(error = AuthError.INVALID_CREDENTIALS) }
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun checkEmailErrors(email: String): Boolean {
        return !email.matches("^[^@]+@[^@]+\\.[^@]+$".toRegex())
    }

    private fun checkPasswordErrors(password: String): Boolean {
        return password.length < 8
    }
}