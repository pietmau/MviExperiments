package com.pppp.usecases.main

import com.badoo.mvicore.element.Actor
import com.pppp.usecases.repository.Repository
import io.reactivex.Observable
import io.reactivex.Scheduler

class MainActor(
    private val repository: Repository,
    private val workerScheduler: Scheduler,
    private val mainThreadSheduler: Scheduler
) :
    Actor<MainFeature.State, MainFeature.Wish, MainFeature.Effect> {

    override fun invoke(
        state: MainFeature.State,
        wish: MainFeature.Wish
    ): Observable<MainFeature.Effect> =
        when (wish) {
            MainFeature.Wish.GetComics -> onGetComicsRequested()
            is MainFeature.Wish.ShowDetail -> onDetailRequested(wish.id, wish.position)
        }

    private fun onDetailRequested(id: String, position: Int): Observable<MainFeature.Effect> =
        Observable.just(MainFeature.Effect.ShowDetail(id, position))

    private fun onGetComicsRequested() = repository.getComics()
        .toObservable()
        .map { MainFeature.Effect.ComicsRetrieved(it) as MainFeature.Effect }
        .subscribeOn(workerScheduler)
        .observeOn(mainThreadSheduler)
        .startWith(MainFeature.Effect.StartedGettingComics)
        .onErrorReturn { MainFeature.Effect.FailureRetrievingComics(it) }

}