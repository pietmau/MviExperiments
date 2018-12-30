package com.pppp.mvicoreapp.detail


import com.pppp.mvicoreapp.detail.view.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.DetailViewModel
import io.reactivex.Observable

typealias UiEventTransformer = (DetailUiEvent) -> DetailFeature.Wish

fun transformUiEvent(eventDetail: DetailUiEvent): DetailFeature.Wish {
    return when (eventDetail) {
        is DetailUiEvent.GetComicData -> DetailFeature.Wish.GetBookDetail(eventDetail.id)
        else -> TODO()
    }
}

typealias DetailViewModelTransformer = (DetailFeature.State) -> DetailViewModel

typealias Reducer = (DetailFeature.State, DetailFeature.Effect) -> DetailFeature.State

fun reduceState(state: DetailFeature.State, effect: DetailFeature.Effect): DetailFeature.State =
    when (effect) {
        is DetailFeature.Effect.GotBookDetail -> DetailFeature.State.GotData(effect.comicsBook)
    }

typealias Bootstrapper<Action> = () -> Observable<Action>

class Boot(private val id: Int) : Bootstrapper<DetailFeature.Wish> {

    override fun invoke(): Observable<DetailFeature.Wish> =
        Observable.just(DetailFeature.Wish.GetBookDetail(id))
}