package com.pppp.mvicoreapp.main

import com.badoo.mvicore.element.Actor
import com.pppp.mvicoreapp.main.MainFeature.*
import com.pppp.mvicoreapp.main.MainFeature.Effect.*
import com.pppp.mvicoreapp.main.MainFeature.Wish.GetComics
import com.pppp.mvicoreapp.main.MainFeature.Wish.ShowDetail
import com.pppp.mvicoreapp.main.model.Repository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//TODO use func composition instead
class MainActor(private val repository: Repository) : Actor<State, Wish, Effect> {

    override fun invoke(state: State, wish: Wish): Observable<Effect> =
        when (wish) {
            GetComics -> onGetComicsRequested()
            is ShowDetail -> onDetailRequested(wish.id)
        }

    private fun onDetailRequested(id: String): Observable<Effect> =
        Observable.just(Effect.ShowDetail(id))

    private fun onGetComicsRequested(): Observable<Effect> {

        return repository.comics.toObservable()
            .map { ComicsRetrieved(it) as Effect }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .startWith(StartedGettingComics)
            .onErrorReturn { FailureRetrievingComics(it) }
    }
}