package com.pppp.database

import com.pppp.entities.Result
import io.reactivex.Observable
import io.reactivex.Single

interface ComicsDatabase {
    fun saveComics(comics: List<Result>)

    fun getComicById(id: Int?): Single<Result>

    fun getAllComics(): Observable<Result>
}