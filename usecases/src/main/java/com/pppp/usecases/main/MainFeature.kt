package com.pppp.usecases.main

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.feature.ActorReducerFeature
import com.pppp.lib.ComicsBook

class MainFeature(actor: Actor<State, Wish, Effect>) :
    ActorReducerFeature<MainFeature.Wish, MainFeature.Effect, MainFeature.State, MainFeature.News>(
        initialState = State.Starting,
        bootstrapper = ::bootstrap,
        actor = actor,
        reducer = ::reduce,
        newsPublisher = ::publishNews
    ) {

    sealed class State {
        object Starting : State()
        object GettingComics : State()
        data class SuccessGettingComics(val comicsBooks: List<ComicsBook>?) : State()
        data class FailureGettingComics(val error: Throwable) : State()
    }

    sealed class Wish {
        data class ShowDetail(val id: String, val position: Int) : Wish()
        object GetComics : Wish()
    }

    sealed class Effect {
        object StartedGettingComics : Effect()
        data class ComicsRetrieved(val comicsBooks: List<ComicsBook>) : Effect()
        data class FailureRetrievingComics(val error: Throwable) : Effect()
        data class ShowDetail(val id: String, val position: Int) : Effect()
    }

    sealed class News {
        data class ShowDetail(val id: Int, val position: Int) : News()
        data class Error(val msg: String?) : News()
    }
}