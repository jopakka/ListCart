package di

import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.app
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import org.koin.dsl.module

internal val firebaseDiModule = module {
    single<FirebaseApp> { Firebase.app }
    single<FirebaseAuth> { Firebase.auth(get()) }
}