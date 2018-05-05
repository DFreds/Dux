package com.bugfreebastard.fluxredux.di

import com.bugfreebastard.fluxredux.AppStore
import com.bugfreebastard.fluxredux.middlewares.NetworkMiddleware
import com.bugfreebastard.fluxredux.network.MovieDbApi
import com.bugfreebastard.fluxredux.storage.MovieDatabase
import com.bugfreebastard.fluxredux.util.ApplicationSchedulerProvider
import com.bugfreebastard.fluxredux.util.SchedulerProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val middlewareModule: Module = applicationContext {
    bean { NetworkMiddleware(movieDbApi = get()) }
}

val storeModule: Module = applicationContext {
    bean { AppStore(schedulerProvider = get(), networkMiddleware = get()) }
}

val schedulerModule: Module = applicationContext {
    bean { ApplicationSchedulerProvider() as SchedulerProvider }
}

val networkModule: Module = applicationContext {
    bean { MovieDbApi.api.create(MovieDbApi::class.java) }
}

val roomModule: Module = applicationContext {
    bean { MovieDatabase.buildDatabase(androidApplication()) }
    bean { get<MovieDatabase>().movieDao() }
}