package com.bugfreebastard.fluxredux.states

import com.bugfreebastard.fluxredux.models.MovieObject
import tw.geothings.rekotlin.StateType

data class MovieListState(
        val movieObjects: List<MovieObject> = emptyList(),
        val isFetching: Boolean = false
) : StateType