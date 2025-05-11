package fi.joonasniemi.listcart.core.data.shoppinglist

import fi.joonasniemi.listcart.core.model.data.ShoppingItem
import fi.joonasniemi.listcart.core.model.data.ShoppingList
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {
    val shoppingLists: Flow<List<ShoppingList>>

    fun shoppingListItems(shoppingListId: String): Flow<List<ShoppingItem>>
}