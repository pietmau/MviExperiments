package com.pppp.database.room

import android.annotation.SuppressLint
import com.pppp.database.ComicsDatabase
import com.pppp.database.database.ComicsDao
import com.pppp.lib.ComicsBook
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

internal class RoomComicsDatabase(
    private val dao: ComicsDao,
    private val scheduler: Scheduler = Schedulers.io()
) : ComicsDatabase {

    /** Fire and forget. */
    @SuppressLint("CheckResult")
    override fun saveComics(comics: List<ComicsBook>) {
        Completable.fromCallable { dao.insertAll(comics) }
            .subscribeOn(scheduler)
            .subscribe({}, {})
    }

    override fun getComicById(id: Int): Single<ComicsBook> =
        Single.fromCallable { dao.getBookById(id) }

    override fun getAllComics(): Observable<ComicsBook> {
        return Observable.fromIterable(dao.getAllBooks())
    }
}