package com.pppp.mvicoreapp.main

import com.pppp.mvicoreapp.main.MainFeature.*
import com.pppp.mvicoreapp.main.MainFeature.Effect.ComicsRetrieved
import com.pppp.mvicoreapp.main.MainFeature.Effect.StartedGettingComics
import com.pppp.mvicoreapp.main.MainFeature.News.ShowDetail
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
        is ComicsRetrieved -> SuccessGettingComics(effect.comicsBooks)
        else -> state
    }

fun bootstrap(): Observable<MainFeature.Wish> = Observable.just(GetComics)

fun publishNews(action: Wish, effect: Effect, state: State): News? =
    when (effect) {
        is Effect.ShowDetail -> try {
            ShowDetail(effect.id.toInt(), effect.position)
        } catch (ex: NumberFormatException) {
            News.Error(ex.localizedMessage)
        }
        else -> null
    }

