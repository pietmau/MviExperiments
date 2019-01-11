package com.pppp.features.detail

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.feature.ActorReducerFeature
import com.pppp.lib.ComicsBook

class DetailFeature(
    actor: Actor<State, Wish, Effect>
) :
    ActorReducerFeature<DetailFeature.Wish, DetailFeature.Effect, DetailFeature.State, DetailFeature.News>(
        initialState = State.Starting,
        actor = actor,
        reducer = ::reduceState
    ) {

    sealed class State {
        object Starting : State()
        object GettingData : State()
        data class GotData(val comicsBook: ComicsBook) : State()
        data class Error(val throwable: Throwable) : State() {
            val errorMessage: String? = throwable.localizedMessage
        }
    }

    sealed class Wish {
        class GetBookDetail(val id: Int) : Wish()
    }

    sealed class Effect {
        data class GotBookDetail(val comicsBook: ComicsBook) : Effect()
        data class Error(val exception: Throwable) : Effect()
    }

    sealed class News
}
