package com.pppp.database

import io.reactivex.Observable
import io.reactivex.Single

interface ComicsDatabase {

    fun saveComics(comics: List<com.pppp.lib.ComicsBook>)

    fun getComicById(id: Int): Single<com.pppp.lib.ComicsBook>

    fun getAllComics(): Observable<com.pppp.lib.ComicsBook>
}