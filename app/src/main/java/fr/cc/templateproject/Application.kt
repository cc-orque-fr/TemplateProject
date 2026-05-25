package fr.cc.templateproject

import android.app.Application
import fr.cc.templateproject.di.networkModule
import fr.cc.templateproject.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class AppTemplate : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppTemplate)

        }
        loadKoinModules(listOf(viewmodelModule, networkModule))
    }
}