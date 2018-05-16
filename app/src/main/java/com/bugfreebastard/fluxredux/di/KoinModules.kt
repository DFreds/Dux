package com.bugfreebastard.fluxredux.di

import com.bugfreebastard.fluxredux.AppStore
import com.bugfreebastard.fluxredux.network.MovieDbApi
import com.bugfreebastard.fluxredux.storage.MovieDatabase
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val storeModule: Module = applicationContext {
    viewModel { AppStore(movieDbApi = get()) }
}

val networkModule: Module = applicationContext {
    bean { MovieDbApi.api.create(MovieDbApi::class.java) }
}

val roomModule: Module = applicationContext {
    bean { MovieDatabase.buildDatabase(androidApplication()) }
    bean { get<MovieDatabase>().movieDao() }
}