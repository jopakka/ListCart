package fi.joonasniemi.listcart.core.data.shoppinglist

import fi.joonasniemi.listcart.core.data.auth.AuthRepository
import fi.joonasniemi.listcart.core.data.shoppinglist.model.ShoppingListItemEntity
import fi.joonasniemi.listcart.core.model.data.ShoppingItem
import fi.joonasniemi.listcart.core.model.data.ShoppingList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
class FirestoreShoppingListRepository(
    private val firestoreShoppingListDataSource: FirestoreShoppingListDataSource,
    private val authRepository: AuthRepository,
) : ShoppingListRepository {
    override val shoppingLists: Flow<List<ShoppingList>> =
        authRepository.userState.map { it?.userId }.distinctUntilChanged().flatMapLatest {
            if (it == null) return@flatMapLatest flowOf(emptyMap())
            firestoreShoppingListDataSource.getShoppingLists(it)
        }.map {
            it.map { (key, entity) ->
                ShoppingList(
                    id = key,
                    name = entity.name.orEmpty(),
                    createdAt = entity.createdAt?.toInstant()?.toEpochMilli() ?: -1,
                )
            }
        }

    override fun shoppingListItems(shoppingListId: String): Flow<List<ShoppingItem>> {
        check(shoppingListId.isNotBlank())

        return authRepository.userState.map { it?.userId }.distinctUntilChanged()
            .flatMapLatest { userId ->
                if (userId == null) return@flatMapLatest flowOf(emptyList())
                firestoreShoppingListDataSource.getShoppingListItems(
                    shoppingListId,
                    userId = userId
                ).map { result ->
                    result.mapNotNull {
                        try {
                            it.value.asShoppingItem(it.key)
                        } catch (e: Exception) {
                            null
                        }
                    }
                }
            }
    }
}

private fun ShoppingListItemEntity.asShoppingItem(id: String): ShoppingItem {
    return ShoppingItem(
        name = name ?: throw IllegalArgumentException("Name cannot be null"),
        id = id,
    )
}