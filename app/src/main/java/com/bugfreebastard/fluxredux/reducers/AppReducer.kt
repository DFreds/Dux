package com.bugfreebastard.fluxredux.reducers

import com.bugfreebastard.fluxredux.actions.MovieListActions
import com.bugfreebastard.fluxredux.states.AppState
import com.bugfreebastard.fluxredux.states.MovieListState
import tw.geothings.rekotlin.Action

fun appReducer(action: Action, state: AppState?): AppState {
    return AppState(
            movieListState = movieListReducer(action, state?.movieListState)
    )
}

fun movieListReducer(action: Action, state: MovieListState?): MovieListState {
    val newState = state ?: MovieListState()

    when (action) {
        MovieListActions.LoadTopRatedMovies -> {
            return newState.copy(isFetching = true)
        }
    }

    return newState
}