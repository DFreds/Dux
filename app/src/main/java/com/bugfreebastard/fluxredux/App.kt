package com.bugfreebastard.fluxredux

import android.app.Application
import com.bugfreebastard.fluxredux.di.*
import com.bugfreebastard.fluxredux.reducers.appReducer
import com.bugfreebastard.fluxredux.states.AppState
import org.koin.android.ext.android.startKoin
import tw.geothings.rekotlin.Store

val appStore = Store(
        state = AppState(),
        reducer = ::appReducer,
        middleware = emptyList(),
        automaticallySkipRepeats = true
)

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