package com.pppp.mvicoreapp.detail

import com.badoo.mvicore.element.Actor
import com.pppp.mvicoreapp.main.model.Repository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ActorImpl(private val repository: Repository) :
    Actor<DetailFeature.State, DetailFeature.Wish, DetailFeature.Effect> {

    //TODO error, etc...
    override fun invoke(
        state: DetailFeature.State,
        wish: DetailFeature.Wish
    ): Observable<DetailFeature.Effect> = when (wish) {
        is DetailFeature.Wish.GetBookDetail -> repository.getComicById(wish.id)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { DetailFeature.Effect.GotBookDetail(it) }
    }
}