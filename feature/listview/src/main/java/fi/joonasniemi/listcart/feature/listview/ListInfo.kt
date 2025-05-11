package fi.joonasniemi.listcart.feature.listview

import kotlinx.serialization.Serializable

@Serializable
data class ListInfo(
    val id: String,
    val name: String,
)
