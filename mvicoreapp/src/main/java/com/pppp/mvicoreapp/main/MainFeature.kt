package com.pppp.mvicoreapp.main

import com.badoo.mvicore.feature.ActorReducerFeature
import com.marvel.marvel.main.model.NetworkServiceRetrofit
import com.marvel.marvel.main.model.pojos.Result
import com.pppp.mvicoreapp.main.MainFeature.*
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel

class MainFeature : ActorReducerFeature<Wish, Effect, State, Nothing>(
    initialState = State.Starting,
    bootstrapper = ::bootstrap,
    actor = MainActor(NetworkServiceRetrofit()),
    reducer = ::reduce
) {

    sealed class State {
        object Starting : State()
        object GettingComics : State()
        data class SuccessGettingComics(val results: List<Result>?) : State()
        data class FailureGettingComics(val error: Throwable) : State()
        data class ShowDetail(val comicsBook: ComicsBookViewModel):State()
    }

    sealed class Wish {
        data class ShowDetail(val comicsBook: ComicsBookViewModel) : Wish()
        object GetComics : Wish()
    }

    sealed class Effect {
        object StartedGettingComics : Effect()
        data class ComicsRetrieved(val results: List<Result>) : Effect()
        data class FailureRetrievingComics(val error: Throwable) : Effect()
        data class ShowDetail(val comicsBook: ComicsBookViewModel) : Effect()
    }

}
