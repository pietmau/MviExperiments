package com.pppp.usecases.detail

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.feature.ActorReducerFeature
import com.pppp.lib.ComicsBook
import com.pppp.usecases.detail.DetailFeature.*

class DetailFeature(
    actor: Actor<State, Wish, Effect>,
    bootstrapper: Bootstrapper<Wish>
) :
    ActorReducerFeature<Wish, Effect, State, News>(
        initialState = State.Starting,
        bootstrapper = bootstrapper,
        actor = actor,
        reducer = ::reduceState
    ) {

    sealed class State {
        object Starting : State()
        object GettingData : State()
        data class GotData(val comicsBook: com.pppp.lib.ComicsBook) : State()
    }

    sealed class Wish {
        class GetBookDetail(val id: Int) : Wish()
    }

    sealed class Effect {
        data class GotBookDetail(val comicsBook: com.pppp.lib.ComicsBook) : Effect()
    }

    sealed class News
}
