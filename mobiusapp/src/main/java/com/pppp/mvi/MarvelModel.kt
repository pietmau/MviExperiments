package com.pppp.mvi

sealed class MarvelModel() {
    object Starting : MarvelModel()
    object Loading : MarvelModel()
    object Foo : MarvelModel()
}