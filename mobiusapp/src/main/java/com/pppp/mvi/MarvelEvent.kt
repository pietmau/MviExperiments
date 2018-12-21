package com.pppp.mvi

sealed class MarvelEvent {
    object Bootstrap : MarvelEvent()
    object Loading : MarvelEvent()
}