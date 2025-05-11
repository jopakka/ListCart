package di

import fi.joonasniemi.listcart.feature.listview.ListViewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val listViewModule = module {
    viewModelOf(::ListViewViewModel)
}