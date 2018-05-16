package com.bugfreebastard.fluxredux

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bugfreebastard.fluxredux.actions.MovieListActions
import com.bugfreebastard.fluxredux.middlewares.fetchTopRatedMovies
import com.bugfreebastard.fluxredux.network.MovieDbApi
import com.bugfreebastard.fluxredux.reducers.appReducer
import com.bugfreebastard.fluxredux.redux.Action

class AppStore(
        private val movieDbApi: MovieDbApi
) : ViewModel() {

    private val _state = MutableLiveData<AppState>().apply { value = AppState(null) }
    val state: LiveData<AppState> = _state

    fun dispatch(action: Action) {
        _state.value = appReducer(action = action, state = _state.value)

        when (action) {
            is MovieListActions.LoadTopRatedMovies -> {
                fetchTopRatedMovies(
                        api = movieDbApi,
                        onSuccess = {
                            dispatch(MovieListActions.InitializeMovieList(it))
                        },
                        onFailure = {
                            println(it?.message)
                            dispatch(MovieListActions.InitializeMovieList(null))
                        }
                )
            }
        }
    }

}