package fi.joonasniemi.listcart.feature.listview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import fi.joonasniemi.listcart.core.data.shoppinglist.ShoppingListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class ListViewViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val listRepository: ShoppingListRepository,
) : ViewModel() {
    private val route = savedStateHandle.toRoute<ListInfo>()

    private val _listInfo = MutableStateFlow(route)
    val listInfo = _listInfo.asStateFlow()

    val listItems = listRepository.shoppingListItems(route.id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList(),
        )
}