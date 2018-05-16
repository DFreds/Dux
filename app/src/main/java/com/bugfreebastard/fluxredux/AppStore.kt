package com.bugfreebastard.fluxredux

import com.bugfreebastard.fluxredux.actions.MovieListActions
import com.bugfreebastard.fluxredux.middlewares.fetchTopRatedMovies
import com.bugfreebastard.fluxredux.network.MovieDbApi
import com.bugfreebastard.fluxredux.reducers.appReducer
import com.bugfreebastard.fluxredux.redux.Action
import com.bugfreebastard.fluxredux.util.SchedulerProvider
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor

class AppStore(
        private val schedulerProvider: SchedulerProvider,
        private val movieDbApi: MovieDbApi
) {

    private val _state = BehaviorProcessor.createDefault(AppState(null))
    val state: Flowable<AppState> = _state

    fun dispatch(action: Action) {
        _state.onNext(appReducer(action = action, state = _state.value))

        when (action) {
            is MovieListActions.LoadTopRatedMovies -> {
                fetchTopRatedMovies(movieDbApi)
                        .subscribeOn(schedulerProvider.io())
                        .map {
                            dispatch(MovieListActions.InitializeMovieList(it))
                        }
                        .observeOn(schedulerProvider.ui())
                        .subscribe()
            }
        }
    }

}