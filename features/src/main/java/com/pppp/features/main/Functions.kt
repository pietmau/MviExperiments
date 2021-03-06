package com.pppp.features.main

import io.reactivex.Observable

fun reduce(
    state: MainFeature.State,
    effect: MainFeature.Effect
) =
    when (effect) {
        MainFeature.Effect.StartedGettingComics -> MainFeature.State.GettingComics
        is MainFeature.Effect.ComicsRetrieved -> MainFeature.State.SuccessGettingComics(effect.comicsBooks)
        is MainFeature.Effect.FailureRetrievingComics -> MainFeature.State.FailureGettingComics(effect.error)
        else -> state
    }

fun bootstrap(): Observable<MainFeature.Wish> = Observable.just(MainFeature.Wish.GetComics)

fun publishNews(
    action: MainFeature.Wish,
    effect: MainFeature.Effect,
    state: MainFeature.State
): MainFeature.News? =
    when (effect) {
        is MainFeature.Effect.ShowDetail -> try {
            MainFeature.News.ShowDetail(effect.id.toInt(), effect.position)
        } catch (ex: NumberFormatException) {
            MainFeature.News.Error(ex.localizedMessage)
        }
        else -> null
    }
