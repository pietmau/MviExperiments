package com.pppp.mvicoreapp.main.view.uieventssource

import com.pppp.mvicoreapp.main.MainFeature.Wish
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer.UiEvent
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer.UiEvent.ComicBookSelected

class UiEventTransformer : (UiEvent) -> Wish? {
    override fun invoke(event: UiEvent): Wish? = when (event) {
        is ComicBookSelected -> Wish.ShowDetail(event.bookId)
        else -> throw UnsupportedOperationException("Unknown wish")
    }

    sealed class UiEvent {
        data class ComicBookSelected(val bookId: String) : UiEvent()
    }
}