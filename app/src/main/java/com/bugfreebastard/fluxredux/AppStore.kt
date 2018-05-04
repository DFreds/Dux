package com.bugfreebastard.fluxredux

import com.bugfreebastard.fluxredux.actions.MovieListActions
import com.bugfreebastard.fluxredux.redux.Action
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor

class AppStore(
        private val middleware: Middleware
) {

    private val _state = BehaviorProcessor.create<AppState>()

    val state: Flowable<AppState> = _state

    private val dispatcherProcessor: FlowableProcessor<Action> = PublishProcessor.create<Action>()

    fun dispatch(action: Action) {
        when (action) {
            is MovieListActions.LoadTopRatedMovies -> {
                dispatcherProcessor.flatMap { middleware.loadTopRatedMovies() }
            }
        }
    }

}