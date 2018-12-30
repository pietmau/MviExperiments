package com.pppp.mvicoreapp.detail

import com.badoo.mvicore.element.Actor
import com.pppp.mvicoreapp.main.model.Repository
import io.reactivex.Observable
import io.reactivex.Scheduler

class DetailActor(
    private val repository: Repository,
    private val workerScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler
) :
    Actor<DetailFeature.State, DetailFeature.Wish, DetailFeature.Effect> {

    //TODO error, etc...
    // TODO use function composition
    override fun invoke(
        state: DetailFeature.State,
        wish: DetailFeature.Wish
    ): Observable<DetailFeature.Effect> = when (wish) {
        is DetailFeature.Wish.GetBookDetail -> repository.getComicById(wish.id)
            .toObservable()
            .subscribeOn(workerScheduler)
            .observeOn(mainThreadScheduler)
            .map { DetailFeature.Effect.GotBookDetail(it) }
    }
}