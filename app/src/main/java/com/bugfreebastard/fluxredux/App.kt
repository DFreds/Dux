package com.bugfreebastard.fluxredux

import android.app.Application
import com.bugfreebastard.fluxredux.di.networkModule
import com.bugfreebastard.fluxredux.di.roomModule
import com.bugfreebastard.fluxredux.di.schedulerModule
import com.bugfreebastard.fluxredux.di.storeModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(
                application = this,
                modules = listOf(
                        storeModule,
                        schedulerModule,
                        networkModule,
                        roomModule
                )
        )
    }
}