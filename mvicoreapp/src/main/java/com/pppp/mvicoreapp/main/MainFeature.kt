package com.pppp.mvicoreapp.main

import android.os.Parcelable
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.feature.ActorReducerFeature
import com.pppp.entities.Result
import com.pppp.mvicoreapp.main.MainFeature.*
import kotlinx.android.parcel.Parcelize

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
        data class SuccessGettingComics(val results: List<Result>?) : State()
        data class FailureGettingComics(val error: Throwable) : State()
    }

    sealed class Wish {
        data class ShowDetail(val id: String) : Wish()
        object GetComics : Wish()
    }

    sealed class Effect {
        object StartedGettingComics : Effect()
        data class ComicsRetrieved(val results: List<Result>) : Effect()
        data class FailureRetrievingComics(val error: Throwable) : Effect()
        data class ShowDetail(val id: String) : Effect()
    }

    sealed class News {
        @Parcelize
        data class ShowDetail(val id: String) : News(), Parcelable
    }

    sealed class Action
}
