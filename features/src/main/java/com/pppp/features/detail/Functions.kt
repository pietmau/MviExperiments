package com.pppp.features.detail

import io.reactivex.Observable

fun reduceState(state: DetailFeature.State, effect: DetailFeature.Effect): DetailFeature.State =
    when (effect) {
        is DetailFeature.Effect.GotBookDetail -> DetailFeature.State.GotData(effect.comicsBook)
        is DetailFeature.Effect.Error -> DetailFeature.State.Error(effect.exception)
    }

typealias Bootstrapper<Action> = () -> Observable<Action>

class Boot(private val id: Int) :
    Bootstrapper<DetailFeature.Wish> {

    override fun invoke(): Observable<DetailFeature.Wish> =
        Observable.just(DetailFeature.Wish.GetBookDetail(id))
}