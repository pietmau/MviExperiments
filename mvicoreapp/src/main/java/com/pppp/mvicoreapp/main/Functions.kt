package com.pppp.mvicoreapp.main

import com.pppp.mvicoreapp.main.MainFeature.Effect
import com.pppp.mvicoreapp.main.MainFeature.Effect.*
import com.pppp.mvicoreapp.main.MainFeature.State
import com.pppp.mvicoreapp.main.MainFeature.State.GettingComics
import com.pppp.mvicoreapp.main.MainFeature.State.SuccessGettingComics
import com.pppp.mvicoreapp.main.MainFeature.Wish.GetComics
import io.reactivex.Observable

fun reduce(
    state: State,
    effect: Effect
) =
    when (effect) {
        StartedGettingComics -> GettingComics
        is ComicsRetrieved -> SuccessGettingComics(effect.results)
        is ShowDetail -> State.ShowDetail(effect.comicsBook)
        else -> state
    }

fun bootstrap(): Observable<MainFeature.Wish> = Observable.just(GetComics)


