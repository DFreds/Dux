package com.bugfreebastard.fluxredux.middlewares

import com.bugfreebastard.fluxredux.models.MovieObject
import com.bugfreebastard.fluxredux.network.MovieDbApi
import io.reactivex.Flowable

fun fetchTopRatedMovies(api: MovieDbApi): Flowable<List<MovieObject>> =
        api.getTopRatedMovies().map { it.results }