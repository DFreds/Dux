package com.bugfreebastard.fluxredux

import android.app.Application
import com.bugfreebastard.fluxredux.di.*
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(
                application = this,
                modules = listOf(
                        middlewareModule,
                        storeModule,
                        schedulerModule,
                        networkModule,
                        roomModule
                )
        )
    }
}