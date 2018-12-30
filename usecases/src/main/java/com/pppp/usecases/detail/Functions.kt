package com.pppp.usecases.detail

import io.reactivex.Observable

typealias Reducer = (DetailFeature.State, DetailFeature.Effect) -> DetailFeature.State

fun reduceState(state: DetailFeature.State, effect: DetailFeature.Effect): DetailFeature.State =
    when (effect) {
        is DetailFeature.Effect.GotBookDetail -> DetailFeature.State.GotData(
            effect.comicsBook
        )
    }

typealias Bootstrapper<Action> = () -> Observable<Action>

class Boot(private val id: Int) :
    Bootstrapper<DetailFeature.Wish> {

    override fun invoke(): Observable<DetailFeature.Wish> =
        Observable.just(DetailFeature.Wish.GetBookDetail(id))
}