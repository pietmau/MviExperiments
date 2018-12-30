package com.pppp.database

import com.pppp.entities.ComicsBook
import io.reactivex.Observable
import io.reactivex.Single

interface ComicsDatabase {

    fun saveComics(comics: List<ComicsBook>)

    fun getComicById(id: Int): Single<ComicsBook>

    fun getAllComics(): Observable<ComicsBook>
}