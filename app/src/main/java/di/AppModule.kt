package di

import fi.joonasniemi.listcart.MainActivityViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    includes(
        dataDiModule,
        featureAuthDiModule,
        listsModule,
    )
    viewModelOf(::MainActivityViewModel)
}