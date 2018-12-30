package com.pppp.mvicoreapp.detail.view.uievent

sealed class DetailUiEvent {
    data class GetComicData(val id: Int) : DetailUiEvent()
}