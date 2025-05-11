package fi.joonasniemi.listcart.core.data.shoppinglist.model

import com.google.firebase.Timestamp

data class ShoppingListEntity(
    val name: String? = null,
    val userId: String? = null,
    val createdAt: Timestamp? = null,
)
