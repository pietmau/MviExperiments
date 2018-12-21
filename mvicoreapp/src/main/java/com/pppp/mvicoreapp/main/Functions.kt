package com.pppp.mvicoreapp.main

import io.reactivex.Observable

fun reduce(
    mainState: MainFeature.MainState,
    mainEffect: MainFeature.MainEffect
): MainFeature.MainState =
    when (mainEffect) {
        MainFeature.MainEffect.StartedGettingComics -> MainFeature.MainState.GettingComics
        is MainFeature.MainEffect.FinishedWithSuccess -> MainFeature.MainState.SuccessGettingComics(
            mainEffect.results
        )
        else -> throw UnsupportedOperationException("Unknown Effect")
    }

fun bootstrap(): Observable<MainFeature.MainWish> = Observable.just(MainFeature.MainWish.GetComics)

fun publish(
    marvelMainWish: MainFeature.MainWish,
    mainEffect: MainFeature.MainEffect,
    mainState: MainFeature.MainState
): MainFeature.News? = when {
    //TODO() -> TODO()
    else -> null
}

