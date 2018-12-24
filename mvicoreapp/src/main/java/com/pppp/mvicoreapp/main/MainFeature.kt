package com.pppp.mvicoreapp.main

import android.os.Parcelable
import com.badoo.mvicore.feature.ActorReducerFeature
import com.marvel.marvel.main.model.NetworkServiceRetrofit
import com.pppp.network.model.pojos.NetworkResult
import com.pppp.mvicoreapp.main.MainFeature.*
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel
import kotlinx.android.parcel.Parcelize

class MainFeature : ActorReducerFeature<Wish, Effect, State, News>(
    initialState = State.Starting,
    bootstrapper = ::bootstrap,
    actor = MainActor(NetworkServiceRetrofit()),
    reducer = ::reduce,
    newsPublisher = ::publishNews
) {

    sealed class State {
        object Starting : State()
        object GettingComics : State()
        data class SuccessGettingComics(val results: List<NetworkResult>?) : State()
        data class FailureGettingComics(val error: Throwable) : State()
    }

    sealed class Wish {
        data class ShowDetail(val comicsBook: ComicsBookViewModel) : Wish()
        object GetComics : Wish()
    }

    sealed class Effect {
        object StartedGettingComics : Effect()
        data class ComicsRetrieved(val results: List<NetworkResult>) : Effect()
        data class FailureRetrievingComics(val error: Throwable) : Effect()
        data class ShowDetail(val comicsBook: ComicsBookViewModel) : Effect()
    }

    sealed class News {
        @Parcelize
        //TODO no no no
        data class ShowDetail(val comicsBook: ComicsBookViewModel) : News(), Parcelable {
            val numberOfPagesAsString = comicsBook.numberOfPagesAsString
            val price = comicsBook.price
            val title = comicsBook.title
            val description = comicsBook.description
            val authors = comicsBook.authors
            val imageUrl = comicsBook.imageUrl
        }
    }

    sealed class Action
}
