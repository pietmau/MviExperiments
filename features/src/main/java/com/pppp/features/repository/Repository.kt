package com.pppp.features.repository

import com.pppp.lib.ComicsBook
import io.reactivex.Single

interface Repository {
    fun getComics(): Single<List<ComicsBook>>

    fun getComicById(id: Int): Single<ComicsBook>
}