package com.bugfreebastard.fluxredux.states

import com.bugfreebastard.fluxredux.models.MovieObject

data class MovieListState(
        val movieObjects: List<MovieObject>
)