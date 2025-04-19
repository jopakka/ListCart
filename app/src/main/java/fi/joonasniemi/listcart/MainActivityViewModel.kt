package fi.joonasniemi.listcart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.joonasniemi.listcart.core.common.LcResult
import fi.joonasniemi.listcart.core.common.asResult
import fi.joonasniemi.listcart.core.data.auth.AuthRepository
import fi.joonasniemi.listcart.core.data.userconfig.UserConfigRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainActivityViewModel(
    userConfigRepository: UserConfigRepository,
    authRepository: AuthRepository,
) : ViewModel() {
    val uiState = userConfigRepository.userConfig
        .map { MainActivityUiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainActivityUiState.Loading,
        )

    val mainState = authRepository.userState
        .asResult()
        .map {
            when (it) {
                is LcResult.Loading -> MainState.Loading
                is LcResult.Error -> MainState.NotAuthenticated
                is LcResult.Success -> {
                    if (it.data != null) MainState.Authenticated
                    else MainState.NotAuthenticated
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainState.Loading,
        )
}