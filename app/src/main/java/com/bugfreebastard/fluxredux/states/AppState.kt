package com.bugfreebastard.fluxredux.states

import com.bugfreebastard.fluxredux.states.MovieListState
import tw.geothings.rekotlin.StateType

data class AppState(
        val movieListState: MovieListState = MovieListState()
) : StateType