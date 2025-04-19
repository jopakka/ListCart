package fi.joonasniemi.listcart.core.data.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import fi.joonasniemi.listcart.core.model.data.UserState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await

internal class FirebaseAuthRepository(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {
    override val userState: Flow<UserState?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(it.currentUser?.asUserState())
        }
        firebaseAuth.addAuthStateListener(authStateListener)

        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }

    override suspend fun login(email: String, password: String) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
         } catch (e: Exception) {
             throw e
         }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    private fun FirebaseUser.asUserState(): UserState {
        return UserState(
            userId = uid,
            displayName = displayName ?: email ?: "User",
        )
    }
}