package com.bugfreebastard.fluxredux

import com.bugfreebastard.fluxredux.states.MovieListState

data class AppState(
        val movieListState: MovieListState?
)