package fi.joonasniemi.listcart.core.data.shoppinglist.model

import com.google.firebase.Timestamp
import fi.joonasniemi.listcart.core.data.serializer.TimestampSerializer
import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListItemEntity(
    val name: String? = null,
    @Serializable(with = TimestampSerializer::class) val createdAt: Timestamp? = null,
    val userId: String? = null,
)
