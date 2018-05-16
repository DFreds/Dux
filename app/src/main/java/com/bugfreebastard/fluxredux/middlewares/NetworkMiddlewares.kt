package com.bugfreebastard.fluxredux.middlewares

import com.bugfreebastard.fluxredux.models.MovieObject
import com.bugfreebastard.fluxredux.models.MovieResponse
import com.bugfreebastard.fluxredux.network.MovieDbApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun fetchTopRatedMovies(
        api: MovieDbApi,
        onSuccess: (List<MovieObject>?) -> Unit,
        onFailure: (Throwable?) -> Unit
) {
    api.getTopRatedMovies().enqueue(object : Callback<MovieResponse> {
        override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
            onFailure.invoke(t)
        }

        override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
            if (response?.isSuccessful == true) {
                onSuccess.invoke(response.body()?.results ?: emptyList())
            } else {
                onFailure.invoke(Throwable(response?.errorBody()?.string()))
            }
        }
    })
}