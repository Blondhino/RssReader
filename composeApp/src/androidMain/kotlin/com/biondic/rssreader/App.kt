package com.biondic.rssreader

import android.app.Application
import com.biondic.rssreader.di.platformModule
import com.biondic.rssreader.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                sharedModule(),
                platformModule(),
            )
        }
    }
}
