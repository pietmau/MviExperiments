package com.pppp.mvicoreapp.detail.view

sealed class DetailUiEvent {
    data class GetComicData(val id: Int) : DetailUiEvent()
}