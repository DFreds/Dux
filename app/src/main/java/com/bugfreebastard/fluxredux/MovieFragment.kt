package com.bugfreebastard.fluxredux

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bugfreebastard.fluxredux.actions.MovieListActions
import com.bugfreebastard.fluxredux.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.movie_fragment.*
import org.koin.android.ext.android.inject

class MovieFragment : Fragment() {

    private val appStore: AppStore by inject()
    private val schedulerProvider: SchedulerProvider by inject()

    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeDisposable = CompositeDisposable()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable.add(appStore.state
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = this::updateUI,
                        onError = this::handleError,
                        onComplete = this::handleComplete
                )
        )

        appStore.dispatch(MovieListActions.LoadTopRatedMovies)
    }

    override fun onDestroy() {
        super.onDestroy()
        println("destroyed")
        compositeDisposable.dispose()
    }

    private fun updateUI(appState: AppState) {
        println("new state $appState")

        when {
            appState.movieListState?.isLoading == true -> movie_tv.text = "Loading"
            appState.movieListState?.movieObjects != null -> movie_tv.text = "${appState.movieListState?.movieObjects.count()} objects"
            else -> movie_tv.text = "Unknown state"
        }
    }

    private fun handleError(throwable: Throwable) {
        movie_tv.text = "Error"
        println("error")
    }

    private fun handleComplete() {
        Toast.makeText(activity, "Complete", Toast.LENGTH_SHORT).show()
        println("complete")
    }
}