package com.pppp.usecases.repository

import com.pppp.lib.ComicsBook
import io.reactivex.Single

interface Repository {
    fun getComics(): Single<List<com.pppp.lib.ComicsBook>>

    fun getComicById(id: Int): Single<com.pppp.lib.ComicsBook>
}