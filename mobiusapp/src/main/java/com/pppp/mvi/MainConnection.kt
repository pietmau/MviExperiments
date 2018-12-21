package com.pppp.mvi

import com.pppp.network.model.MarvelViewModel
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer

class MainConnection(
    val events: Consumer<MarvelEvent>,
    val marvelViewModel: MarvelViewModel
) :
    Connection<MarvelEffect> {

    override fun accept(effect: MarvelEffect) {
//        when (effect) {
//            MarvelEffect.GetComics -> TODO()
//        }
    }

    override fun dispose() { /* NoOp */

    }
}