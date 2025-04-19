package fi.joonasniemi.listcart.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fi.joonasniemi.listcart.core.designsystem.components.LcButton
import fi.joonasniemi.listcart.core.designsystem.components.LcSecureTextField
import fi.joonasniemi.listcart.core.designsystem.components.LcTextField
import fi.joonasniemi.listcart.core.designsystem.theme.ListCartTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthRoot(
    onAuthenticated: () -> Unit,
    viewModel: AuthViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.run() }

    LaunchedEffect(state.isLoginSuccess) { onAuthenticated() }

    AuthScreen(
        state = state,
        onAction = viewModel::onAction,
    )
}

@Composable
fun AuthScreen(
    state: AuthState,
    onAction: (AuthAction) -> Unit,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val errorMessage = remember(state.error) {
        val res = when (state.error) {
            null -> null
            AuthError.INVALID_CREDENTIALS -> R.string.feature_auth_invalid_credentials
            else -> R.string.feature_auth_unknown_error
        }
        if (res != null) context.getString(res)
        else ""
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        ) {
            LcTextField(
                state = state.email,
                label = stringResource(R.string.feature_auth_email),
                leadingIcon = { Icon(Icons.Default.Email, null) },
                error = state.hasEmailError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                onKeyboardAction = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
            )
            LcSecureTextField(
                state = state.password,
                label = stringResource(R.string.feature_auth_password),
                leadingIcon = { Icon(Icons.Default.Lock, null) },
                error = state.hasPasswordError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                onKeyboardAction = {
                    focusManager.clearFocus()
                    if (state.isValid) onAction(AuthAction.LoginClicked)
                },
            )

            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
            )

            LcButton(
                onClick = { onAction(AuthAction.LoginClicked) },
                enabled = state.isValid && !state.isLoading,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(ButtonDefaults.IconSpacing),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            strokeWidth = 2.dp,
                        )
                    }
                    Text(stringResource(R.string.feature_auth_login))
                }
            }
        }
    }
}

@Preview
@Composable
private fun AuthScreenPreview() {
    ListCartTheme {
        AuthScreen(
            state = AuthState(),
            onAction = {},
        )
    }
}