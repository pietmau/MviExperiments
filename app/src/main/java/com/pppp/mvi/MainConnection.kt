package com.pppp.mvi

import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer

class MainConnection(val events: Consumer<MarvelEvent>) :
    Connection<MarvelEffect> {

    override fun accept(value: MarvelEffect) {

    }

    override fun dispose() { /* NoOp */

    }
}