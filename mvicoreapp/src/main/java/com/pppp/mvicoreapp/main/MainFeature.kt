package com.pppp.mvicoreapp.main

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.feature.ActorReducerFeature
import com.marvel.marvel.main.model.NetworkService
import com.marvel.marvel.main.model.NetworkServiceRetrofit
import com.marvel.marvel.main.model.pojos.Result
import com.pppp.mvicoreapp.main.MainFeature.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFeature : ActorReducerFeature<MainWish, MainEffect, MainState, News>(
    initialState = MainState.Starting,
    bootstrapper = ::bootstrap,
    actor = MainActor(NetworkServiceRetrofit()),
    reducer = ::reduce,
    newsPublisher = ::publish
) {

    sealed class MainState {
        object Starting : MainState()
        object GettingComics : MainState()
        class SuccessGettingComics(val results: List<Result>?) : MainState()
        class FailureGettingComics(val error: Throwable) : MainState()
    }

    sealed class MainWish {
        object GetComics : MainWish()
    }

    sealed class News

    sealed class MainEffect {
        object StartedGettingComics : MainEffect()
        data class FinishedWithSuccess(val results: List<Result>?) : MainEffect()
        data class FinishedWithError(val error: Throwable) : MainEffect()
    }

    class MainActor(private val service: NetworkService) : //TODO use func composition to use a function instead
        Actor<MainState, MainWish, MainEffect> {
        override fun invoke(
            mainState: MainState,
            marvelMainWish: MainWish
        ): Observable<MainEffect> = when (marvelMainWish) {
            MainWish.GetComics -> {
                service.comics.map { MainEffect.FinishedWithSuccess(it.data?.results) as MainEffect }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(MainEffect.StartedGettingComics)
                    .onErrorReturn { MainEffect.FinishedWithError(it) }
            }
            else -> throw UnsupportedOperationException("Unknown Wish")
        }
    }
}
