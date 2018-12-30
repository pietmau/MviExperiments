package com.pppp.usecases.main

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.feature.ActorReducerFeature
import com.pppp.lib.ComicsBook
import com.pppp.usecases.main.MainFeature.*

class MainFeature(val actor: Actor<State, Wish, Effect>) :
    ActorReducerFeature<Wish, Effect, State, News>(
        initialState = State.Starting,
        bootstrapper = ::bootstrap,
        actor = actor,
        reducer = ::reduce,
        newsPublisher = ::publishNews
    ) {

    sealed class State {
        object Starting : State()
        object GettingComics : State()
        data class SuccessGettingComics(val comicsBooks: List<com.pppp.lib.ComicsBook>?) : State()
        data class FailureGettingComics(val error: Throwable) : State()
    }

    sealed class Wish {
        data class ShowDetail(val id: String, val position: Int) : Wish()//TODO should be a Int
        object GetComics : Wish()
    }

    sealed class Effect {
        object StartedGettingComics : Effect()
        data class ComicsRetrieved(val comicsBooks: List<com.pppp.lib.ComicsBook>) : Effect()
        data class FailureRetrievingComics(val error: Throwable) : Effect()
        data class ShowDetail(val id: String, val position: Int) : Effect()
    }

    sealed class News {
        data class ShowDetail(val id: Int, val position: Int) : News()
        data class Error(val msg: String?) : News()
    }
}