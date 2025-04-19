package fi.joonasniemi.listcart.core.data.userconfig

import fi.joonasniemi.listcart.core.model.data.UserConfig
import kotlinx.coroutines.flow.Flow

interface UserConfigRepository {
    val userConfig: Flow<UserConfig>
}