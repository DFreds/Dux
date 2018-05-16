package com.bugfreebastard.fluxredux.actions

import com.bugfreebastard.fluxredux.models.MovieObject
import com.bugfreebastard.fluxredux.redux.Action

sealed class MovieListActions : Action {
    object LoadTopRatedMovies : MovieListActions()
    data class InitializeMovieList(val movieObjects: List<MovieObject>?) : MovieListActions()

    data class AddMovieToFavorites(val movieObject: MovieObject) : MovieListActions()
    data class RemoveMovieFromFavorites(val movieObject: MovieObject) : MovieListActions()
}