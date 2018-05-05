package com.bugfreebastard.fluxredux.middlewares

import com.bugfreebastard.fluxredux.models.MovieObject
import com.bugfreebastard.fluxredux.network.MovieDbApi
import io.reactivex.Flowable

class NetworkMiddleware(
        private val movieDbApi: MovieDbApi
) {
    fun loadTopRatedMovies(): Flowable<List<MovieObject>> {
        return movieDbApi.getTopRatedMovies().map { it.results }
    }
}
