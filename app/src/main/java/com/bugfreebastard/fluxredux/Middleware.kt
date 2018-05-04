package com.bugfreebastard.fluxredux

import com.bugfreebastard.fluxredux.models.MovieObject
import com.bugfreebastard.fluxredux.network.MovieDbApi
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class Middleware(
        private val movieDbApi: MovieDbApi
) {
    fun loadTopRatedMovies(): Flowable<List<MovieObject>> {
        return movieDbApi.getTopRatedMovies()
                .subscribeOn(Schedulers.io())
                .map {
                    it.results
                }
    }
}
