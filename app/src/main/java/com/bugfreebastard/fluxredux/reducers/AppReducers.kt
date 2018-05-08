package com.bugfreebastard.fluxredux.reducers

import com.bugfreebastard.fluxredux.actions.MovieListActions
import com.bugfreebastard.fluxredux.redux.Action
import com.bugfreebastard.fluxredux.AppState
import com.bugfreebastard.fluxredux.states.MovieListState

fun appReducer(action: Action, state: AppState): AppState {
    return state.copy(
            movieListState = movieListReducer(action, state.movieListState)
    )
}

fun movieListReducer(action: Action, state: MovieListState?): MovieListState? {
    val newState = state ?: MovieListState()

    when (action) {
        is MovieListActions.LoadTopRatedMovies -> {
            return newState.copy(isLoading = true, movieObjects = null)
        }
        is MovieListActions.InitializeMovieList -> {
            return newState.copy(isLoading = false, movieObjects = action.movieObjects)
        }
    }

    return newState
}
