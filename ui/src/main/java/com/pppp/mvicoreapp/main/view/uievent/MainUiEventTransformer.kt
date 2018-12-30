package com.pppp.mvicoreapp.main.view.uievent

import com.pppp.usecases.main.MainFeature

class MainUiEventTransformer : (MainUiEvent) -> MainFeature.Wish? {
    override fun invoke(event: MainUiEvent): MainFeature.Wish? = when (event) {
        is MainUiEvent.ComicBookSelected -> MainFeature.Wish.ShowDetail(event.bookId, event.position)
        else -> throw UnsupportedOperationException("Unknown wish")
    }
}