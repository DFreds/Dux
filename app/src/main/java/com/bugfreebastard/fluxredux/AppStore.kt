package com.bugfreebastard.fluxredux

import com.bugfreebastard.fluxredux.actions.MovieListActions
import com.bugfreebastard.fluxredux.redux.Action
import com.bugfreebastard.fluxredux.states.MovieListState
import com.bugfreebastard.fluxredux.util.SchedulerProvider
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor

class AppStore(
        private val schedulerProvider: SchedulerProvider,
        private val networkMiddleware: NetworkMiddleware
) {

    private val _state = BehaviorProcessor.createDefault(AppState(null))
    val state: Flowable<AppState> = _state

    private val dispatcherProcessor: FlowableProcessor<Action> = PublishProcessor.create<Action>()

    // todo need reducers
    fun dispatch(action: Action) {
        when (action) {
            is MovieListActions.LoadTopRatedMovies -> {
                networkMiddleware.loadTopRatedMovies()
                        .subscribeOn(schedulerProvider.io())
                        .map { _state.value.copy(movieListState = MovieListState(it)) }
                        .observeOn(schedulerProvider.ui())
                        .subscribe(_state)

                // not sure why this doesn't work yet
//                dispatcherProcessor
//                        .subscribeOn(schedulerProvider.io())
//                        .flatMap { networkMiddleware.loadTopRatedMovies() }
//                        .map { _state.value.copy(movieListState = MovieListState(it)) }
//                        .observeOn(schedulerProvider.ui())
//                        .subscribe(_state)
            }
        }
    }

}