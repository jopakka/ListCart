package fi.joonasniemi.listcart.core.data.auth

import fi.joonasniemi.listcart.core.model.data.UserState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val userState: Flow<UserState?>

    suspend fun login(email: String, password: String)

    fun logout()
}