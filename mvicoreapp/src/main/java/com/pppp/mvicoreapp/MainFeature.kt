package com.pppp.mvicoreapp

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.marvel.marvel.main.model.NetworkService
import com.marvel.marvel.main.model.NetworkServiceRetrofit
import com.marvel.marvel.main.model.pojos.Result
import com.pppp.mvicoreapp.MainFeature.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFeature : ActorReducerFeature<MainWish, MainEffect, MainState, News>(
    initialState = MainState.Starting,
    bootstrapper = BootStrapperImpl(),
    actor = MainActorImpl(NetworkServiceRetrofit()),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    sealed class MainState {
        object Starting : MainState()
        object LoadingComics : MainState()
        class SuccessGettingComics(val results: List<Result>?) : MainState()
        class FailureGettingComics : MainState()
    }

    sealed class MainWish {
        object GetComics : MainWish()
    }

    sealed class MainEffect {
        object StartedGettingComics : MainEffect()
        data class FinishedWithSuccess(val results: List<Result>?) : MainEffect()
        data class FinishedWithError(val error: Throwable) : MainEffect()
    }

    sealed class News

    class BootStrapperImpl : Bootstrapper<MainWish> {
        override fun invoke(): Observable<MainWish> = Observable.just(MainWish.GetComics)
    }

    class MainActorImpl(private val service: NetworkService) :
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

    class ReducerImpl : Reducer<MainState, MainEffect> {
        override fun invoke(mainState: MainState, mainEffect: MainEffect): MainState =
            when (mainEffect) {
                MainEffect.StartedGettingComics -> MainState.LoadingComics
                is MainEffect.FinishedWithSuccess -> MainState.SuccessGettingComics(mainEffect.results)
                else -> throw UnsupportedOperationException("Unknown Effect")
            }
    }

    class NewsPublisherImpl : NewsPublisher<MainWish, MainEffect, MainState, News> {
        override fun invoke(
            marvelMainWish: MainWish,
            mainEffect: MainEffect,
            mainState: MainState
        ): News? = when {
            //TODO() -> TODO()
            else -> null
        }
    }
}
