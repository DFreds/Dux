package com.bugfreebastard.fluxredux.network

import com.bugfreebastard.fluxredux.models.MovieResponse
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbApi {
    companion object {
        private const val API_KEY = ""
        private const val BASE_URL = "http://api.themoviedb.org/3/"

        val api: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @GET("movie/top_rated?api_key=${MovieDbApi.API_KEY}")
    fun getTopRatedMovies(): Flowable<MovieResponse>

    @GET("discover/movie?api_key=${MovieDbApi.API_KEY}&sort_by=popularity.desc")
    fun discoverMovies(): Flowable<MovieResponse>

    @GET("movie/{id}?api_key=${MovieDbApi.API_KEY}")
    fun getMovieDetails(@Path("id") id: Int): Flowable<MovieResponse>
}