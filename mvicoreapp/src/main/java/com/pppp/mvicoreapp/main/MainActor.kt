package com.pppp.mvicoreapp.main

import com.badoo.mvicore.element.Actor
import com.pppp.mvicoreapp.main.MainFeature.*
import com.pppp.mvicoreapp.main.MainFeature.Effect.*
import com.pppp.mvicoreapp.main.MainFeature.Wish.GetComics
import com.pppp.mvicoreapp.main.MainFeature.Wish.ShowDetail
import com.pppp.mvicoreapp.main.model.Repository
import io.reactivex.Observable
import io.reactivex.Scheduler

class MainActor(
    private val repository: Repository,
    private val workerScheduler: Scheduler,
    private val mainThreadSheduler: Scheduler
) :
    Actor<State, Wish, Effect> {

    override fun invoke(state: State, wish: Wish): Observable<Effect> =
        when (wish) {
            GetComics -> onGetComicsRequested()
            is ShowDetail -> onDetailRequested(wish.id, wish.position)
        }

    private fun onDetailRequested(id: String, position: Int): Observable<Effect> =
        Observable.just(Effect.ShowDetail(id, position))

    private fun onGetComicsRequested() = repository.getComics()
        .toObservable()
        .map { ComicsRetrieved(it) as Effect }
        .subscribeOn(workerScheduler)
        .observeOn(mainThreadSheduler)
        .startWith(StartedGettingComics)
        .onErrorReturn { FailureRetrievingComics(it) }

}