package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AppApplication)
            modules(appModules)
        }
    }
}