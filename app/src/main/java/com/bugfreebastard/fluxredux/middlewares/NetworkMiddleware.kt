package com.bugfreebastard.fluxredux.middlewares

import com.bugfreebastard.fluxredux.actions.MovieListActions
import com.bugfreebastard.fluxredux.models.MovieResponse
import com.bugfreebastard.fluxredux.network.MovieDbApi
import com.bugfreebastard.fluxredux.states.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tw.geothings.rekotlin.DispatchFunction
import tw.geothings.rekotlin.Middleware

internal val networkMiddleware: Middleware<AppState> = { dispatch, getState ->
    { next ->
        { action ->
            when (action) {
                is MovieListActions.LoadTopRatedMovies -> {
                    callTopRatedMovies(dispatch)
                }
            }

            next(action)
        }
    }
}

private fun callTopRatedMovies(dispatch: DispatchFunction) {
    val apiService = MovieDbApi.api.create(MovieDbApi::class.java)
    val call = apiService.getTopRatedMovies()

    call.enqueue(object : Callback<MovieResponse> {
        override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
            println("failed to get data")
        }

        override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
            val movieObjects = response?.body()?.results
            movieObjects?.let {
                dispatch(MovieListActions.InitializeMovieList(it))
            }
        }
    })
}
