package com.bugfreebastard.fluxredux.actions

import com.bugfreebastard.fluxredux.models.MovieObject
import tw.geothings.rekotlin.Action

sealed class MovieListActions : Action {
    object LoadTopRatedMovies : MovieListActions()
    data class InitializeMovieList(val movieObjects: List<MovieObject>) : MovieListActions()

    data class AddMovieToFavorites(val movieObject: MovieObject) : MovieListActions()
    data class RemoveMovieFromFavorites(val movieObject: MovieObject) : MovieListActions()
}