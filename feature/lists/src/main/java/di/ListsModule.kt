package di

import fi.joonasniemi.listcart.feature.lists.ListsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val listsModule = module {
    viewModelOf(::ListsViewModel)
}