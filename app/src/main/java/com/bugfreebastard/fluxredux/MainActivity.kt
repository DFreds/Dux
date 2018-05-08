package com.bugfreebastard.fluxredux

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bugfreebastard.fluxredux.actions.MovieListActions
import com.bugfreebastard.fluxredux.states.AppState
import com.bugfreebastard.fluxredux.states.MovieListState
import tw.geothings.rekotlin.StoreSubscriber

class MainActivity : AppCompatActivity(), StoreSubscriber<MovieListState> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appStore.subscribe(this) {
            it.select {
                it.movieListState
            }.skipRepeats { oldState, newState ->
                oldState == newState
            }
        }

        appStore.dispatch(MovieListActions.LoadTopRatedMovies)
    }

    override fun onDestroy() {
        super.onDestroy()
        appStore.unsubscribe(this)
    }

    override fun newState(state: MovieListState) {
        if (state.isFetching) {
            println("show loading")
        } else {
            println("don't show loading")
        }
    }

    private fun updateUi(appState: AppState) {
        println("new state $appState")
    }

    private fun handleError(throwable: Throwable) {
        println("error")
    }

    private fun handleComplete() {
        println("complete")
    }
}
