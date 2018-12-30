package com.pppp.database

import android.content.Context
import com.pppp.database.database.ComicsDao
import com.pppp.database.database.RoomDb
import com.pppp.entities.ComicsBook
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/** Room with RxJava requires AndroidX */
class ComicsDatabaseImpl(context: Context) : ComicsDatabase {
    private val dao: ComicsDao = RoomDb.db(context).dao()

    /** Fire and forget. */
    override fun saveComics(comics: List<ComicsBook>) { //TODO move stuffa bit here
        Completable.fromCallable { dao.insert(comics) }.subscribe({}, {})
    }

    override fun getComicById(id: Int?): Single<ComicsBook> =
        Single.fromCallable { dao.getComicById(id) }

    override fun getAllComics(): Observable<ComicsBook> {
        val allComics = dao.getAllComics()
        return Observable.fromIterable(allComics)
    }
}