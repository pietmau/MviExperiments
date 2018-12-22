package com.pppp.mvicoreapp.main

import com.badoo.mvicore.element.Actor
import com.marvel.marvel.main.model.NetworkService
import com.marvel.marvel.main.model.pojos.Comics
import com.pppp.mvicoreapp.main.MainFeature.*
import com.pppp.mvicoreapp.main.MainFeature.Effect.*
import com.pppp.mvicoreapp.main.MainFeature.Wish.GetComics
import com.pppp.mvicoreapp.main.MainFeature.Wish.ShowDetail
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//TODO use func composition instead
class MainActor(private val service: NetworkService) : Actor<State, Wish, Effect> {

    override fun invoke(state: State, wish: Wish): Observable<Effect> =
        when (wish) {
            GetComics -> onGetComicsRequested()
            is ShowDetail -> onDetailRequested(wish.comicsBook)
        }

    private fun onDetailRequested(comicsBook: ComicsBookViewModel): Observable<Effect> =
        Observable.just(Effect.ShowDetail(comicsBook))

    private fun onGetComicsRequested(): Observable<Effect> {
        fun getResultsList(it: Comics) = (it.data?.results ?: emptyList())

        return service.comics
            .map { ComicsRetrieved(getResultsList(it)) as Effect }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .startWith(StartedGettingComics)
            .onErrorReturn { FailureRetrievingComics(it) }
    }
}