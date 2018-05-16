package com.bugfreebastard.fluxredux

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bugfreebastard.fluxredux.actions.MovieListActions
import kotlinx.android.synthetic.main.movie_fragment.*
import org.koin.android.architecture.ext.viewModel

class MovieFragment : Fragment() {

    private val appStore: AppStore by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appStore.state.observe(this, Observer {
            updateUI(it)
        })

        appStore.dispatch(MovieListActions.LoadTopRatedMovies)
    }

    private fun updateUI(appState: AppState?) {
        println("new state $appState")

        when {
            appState?.movieListState?.isLoading == true -> movie_tv.text = "Loading"
            appState?.movieListState?.movieObjects != null -> movie_tv.text = "${appState?.movieListState?.movieObjects.count()} objects"
            else -> movie_tv.text = "Unknown state"
        }
    }
}