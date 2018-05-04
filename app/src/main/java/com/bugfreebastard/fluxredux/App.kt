package com.bugfreebastard.fluxredux

import android.app.Application
import com.bugfreebastard.fluxredux.di.networkModule
import com.bugfreebastard.fluxredux.di.roomModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(networkModule, roomModule))
    }
}