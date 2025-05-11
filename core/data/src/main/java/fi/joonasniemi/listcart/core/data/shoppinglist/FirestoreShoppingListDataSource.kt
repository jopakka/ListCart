package fi.joonasniemi.listcart.core.data.shoppinglist

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import fi.joonasniemi.listcart.core.data.shoppinglist.model.ShoppingListEntity
import fi.joonasniemi.listcart.core.data.shoppinglist.model.ShoppingListItemEntity
import io.github.aakira.napier.Napier
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirestoreShoppingListDataSource(
    private val db: FirebaseFirestore,
) {
    fun getShoppingLists(userId: String) = callbackFlow {
        val collection = db.collection("shoppingLists")
            .whereEqualTo("userId", userId)

        val results = mutableMapOf<String, ShoppingListEntity>()

        val listener = collection.addSnapshotListener { value, e ->
            if (e != null) {
                Napier.e("Error getting shopping lists", e)
                return@addSnapshotListener
            }

            value?.documentChanges?.forEach {
                when (it.type) {
                    DocumentChange.Type.ADDED,
                    DocumentChange.Type.MODIFIED -> {
                        val entity = it.document.toObject<ShoppingListEntity>()
                        results[it.document.id] = entity
                    }

                    DocumentChange.Type.REMOVED -> results.remove(it.document.id)
                }
            }

            trySend(results.toMap())
        }

        awaitClose {
            listener.remove()
        }
    }

    fun getShoppingListItems(shoppingListId: String, userId: String): Flow<Map<String, ShoppingListItemEntity>> =
        callbackFlow {
            val collection = db.collection("shoppingItems")
                .whereEqualTo("shoppingListId", shoppingListId)
                .whereEqualTo("userId", userId)
                .orderBy("createdAt")
                .limitToLast(1000)

            val results = mutableMapOf<String, ShoppingListItemEntity>()

            val listener = collection.addSnapshotListener { value, error ->
                if (error != null) {
                    Napier.e("Error getting shopping list items", error)
                    return@addSnapshotListener
                }

                value?.documentChanges?.forEach {
                    when (it.type) {
                        DocumentChange.Type.ADDED,
                        DocumentChange.Type.MODIFIED -> {
                            val entity = it.document.toObject<ShoppingListItemEntity>()
                            results[it.document.id] = entity
                        }

                        DocumentChange.Type.REMOVED -> results.remove(it.document.id)
                    }
                }
                trySend(results.toMap())
            }

            awaitClose {
                listener.remove()
            }
        }
}