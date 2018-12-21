package com.pppp.mvi

import com.pppp.network.model.MarvelViewModel
import com.spotify.mobius.*
import com.spotify.mobius.Next.next

fun update(model: MarvelModel, event: MarvelEvent): Next<MarvelModel, MarvelEffect> {

    when (event) {
        MarvelEvent.Bootstrap -> return next(MarvelModel.Loading, setOf(MarvelEffect.GetComics))

        else -> throw UnsupportedOperationException("Unknown state")
    }
}

fun initLoop(marvelViewModel: MarvelViewModel): First<MarvelModel, MarvelEffect> {
    return if (marvelViewModel.request == null) {
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

fun createLoop(marvelViewModel: MarvelViewModel): MobiusLoop<MarvelModel, MarvelEvent, MarvelEffect> {
    return Mobius
        .loop(
            Update<MarvelModel, MarvelEvent, MarvelEffect>
            { model, event ->
                update(model, event)
            },
            Connectable<MarvelEffect, MarvelEvent> { events ->
                MainConnection(events, marvelViewModel)
            })
        .init { model ->
            initLoop(marvelViewModel)
        }
        .startFrom(MarvelModel.Starting)
}
