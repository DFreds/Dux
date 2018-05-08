package com.bugfreebastard.fluxredux.states

import com.bugfreebastard.fluxredux.models.MovieObject

data class MovieListState(
        val isLoading: Boolean = false,
        val movieObjects: List<MovieObject>? = null
)