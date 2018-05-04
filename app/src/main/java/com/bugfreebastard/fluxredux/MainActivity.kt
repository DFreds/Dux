package com.bugfreebastard.fluxredux

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bugfreebastard.fluxredux.actions.MovieListActions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val appStore: AppStore by inject()
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()

        compositeDisposable.add(appStore.state
                .subscribeBy(
                        onNext = this::updateUi,
                        onError = this::handleError,
                        onComplete = this::handleComplete
                )
        )

        appStore.dispatch(MovieListActions.LoadTopRatedMovies)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
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
