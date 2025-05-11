package fi.joonasniemi.listcart.feature.lists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.joonasniemi.listcart.core.data.shoppinglist.ShoppingListRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ListsViewModel(
    private val shoppingListRepository: ShoppingListRepository,
) : ViewModel() {
    val shoppingLists = shoppingListRepository.shoppingLists
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )
}