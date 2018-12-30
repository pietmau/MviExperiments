package com.pppp.mvicoreapp.main.view.uievent

sealed class MainUiEvent {
    data class ComicBookSelected(val bookId: String, val position: Int) : MainUiEvent()
}