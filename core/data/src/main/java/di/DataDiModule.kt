package di

import fi.joonasniemi.listcart.core.data.auth.AuthRepository
import fi.joonasniemi.listcart.core.data.auth.FirebaseAuthRepository
import fi.joonasniemi.listcart.core.data.userconfig.LocalUserConfigRepository
import fi.joonasniemi.listcart.core.data.userconfig.UserConfigRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataDiModule = module {
    includes(firebaseDiModule)
    singleOf(::FirebaseAuthRepository) bind AuthRepository::class
    singleOf(::LocalUserConfigRepository) bind UserConfigRepository::class
}