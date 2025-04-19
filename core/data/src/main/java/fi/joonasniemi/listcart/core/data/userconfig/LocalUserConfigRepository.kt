package fi.joonasniemi.listcart.core.data.userconfig

import fi.joonasniemi.listcart.core.model.data.UserConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class LocalUserConfigRepository : UserConfigRepository {
    private val _userConfig = MutableStateFlow(UserConfig())
    override val userConfig: Flow<UserConfig> = _userConfig.asStateFlow()

    fun t() {

    }
}