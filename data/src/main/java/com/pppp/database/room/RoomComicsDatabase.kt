package com.pppp.database.room

import android.annotation.SuppressLint
import android.content.Context
import com.pppp.database.ComicsDatabase
import com.pppp.database.database.ComicsDao
import com.pppp.database.database.RoomDb
import com.pppp.lib.ComicsBook
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

internal class RoomComicsDatabase(context: Context) : ComicsDatabase {
    private val dao: ComicsDao = RoomDb.db(context).dao()

    /** Fire and forget. */
    @SuppressLint("CheckResult")
    override fun saveComics(comics: List<ComicsBook>) {
        Completable.fromCallable { dao.insertAll(comics) }.subscribe({}, {})
    }

    override fun getComicById(id: Int): Single<ComicsBook> =
        Single.fromCallable { dao.getBookById(id) }

    override fun getAllComics(): Observable<ComicsBook> {
        val allComics = dao.getAllBooks()
        return Observable.fromIterable(allComics)
    }
}