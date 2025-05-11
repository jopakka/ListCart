package di

import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.app
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import org.koin.dsl.module

internal val firebaseDiModule = module {
    single<FirebaseApp> { Firebase.app }
    single<FirebaseAuth> { Firebase.auth(get()) }
    single<FirebaseFirestore> { Firebase.firestore(get<FirebaseApp>()) }
}