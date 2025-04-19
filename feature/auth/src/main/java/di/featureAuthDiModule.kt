package di

import fi.joonasniemi.listcart.feature.auth.AuthViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureAuthDiModule = module {
    viewModelOf(::AuthViewModel)
}