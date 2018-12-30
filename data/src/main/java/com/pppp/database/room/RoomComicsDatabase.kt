package com.pppp.database.room

import android.content.Context
import com.pppp.database.ComicsDatabase
import com.pppp.database.database.ComicsDao
import com.pppp.database.database.RoomDb
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/** Room with RxJava requires AndroidX */
internal class RoomComicsDatabase(context: Context) : ComicsDatabase {
    private val dao: ComicsDao = RoomDb.db(context).dao()

    /** Fire and forget. */
    override fun saveComics(comics: List<com.pppp.lib.ComicsBook>) { //TODO move stuffa bit here
        Completable.fromCallable { dao.insertAll(comics) }.subscribe({}, {})
    }

    override fun getComicById(id: Int): Single<com.pppp.lib.ComicsBook> =
        Single.fromCallable { dao.getBookById(id) }

    override fun getAllComics(): Observable<com.pppp.lib.ComicsBook> {
        val allComics = dao.getAllBooks()
        return Observable.fromIterable(allComics)
    }
}