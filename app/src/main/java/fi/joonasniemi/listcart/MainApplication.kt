package fi.joonasniemi.listcart

import android.app.Application
import di.appModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Napier.base(DebugAntilog())

        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}