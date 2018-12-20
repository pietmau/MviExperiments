package com.pppp.mvi.network.model

import android.os.Bundle
import com.pppp.mvi.MainConnection
import com.pppp.mvi.MarvelEffect
import com.pppp.mvi.MarvelEvent
import com.pppp.mvi.MarvelModel
import com.spotify.mobius.*
import com.spotify.mobius.Next.next

fun update(model: MarvelModel, event: MarvelEvent): Next<MarvelModel, MarvelEffect> {

    when (event) {
        MarvelEvent.Bootstrap -> return next(MarvelModel.Loading, setOf(MarvelEffect.GetComics))

        else -> throw UnsupportedOperationException("Unknown state")
    }
}

fun initLoop(savedInstanceState: Bundle?): First<MarvelModel, MarvelEffect> {
    return if (savedInstanceState == null) {
        First.first(
            MarvelModel.Loading,
            setOf(MarvelEffect.GetComics)
        )
    } else {
        First.first(
            MarvelModel.Foo,
            emptySet()
        )
    }
}

fun createLoop(savedInstanceState: Bundle?): MobiusLoop<MarvelModel, MarvelEvent, MarvelEffect> {
    return Mobius
        .loop(
            Update<MarvelModel, MarvelEvent, MarvelEffect>
            { model, event ->
                update(model, event)
            },
            Connectable<MarvelEffect, MarvelEvent> { events -> MainConnection(events) })
        .init { model ->
            initLoop(savedInstanceState)
        }
        .startFrom(MarvelModel.Starting)
}
