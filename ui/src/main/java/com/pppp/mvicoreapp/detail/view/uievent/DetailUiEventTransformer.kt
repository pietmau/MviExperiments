package com.pppp.mvicoreapp.detail.view.uievent

import com.pppp.usecases.detail.DetailFeature

class DetailUiEventTransformerImpl : DetailUiEventTransformer {
    override fun invoke(eventDetail: DetailUiEvent): DetailFeature.Wish {
        return when (eventDetail) {
            is DetailUiEvent.GetComicData -> DetailFeature.Wish.GetBookDetail(
                eventDetail.id
            )
            else -> TODO()
        }
    }
}

typealias DetailUiEventTransformer = (DetailUiEvent) -> DetailFeature.Wish