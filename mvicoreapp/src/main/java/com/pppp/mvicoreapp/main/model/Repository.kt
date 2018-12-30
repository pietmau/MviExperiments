package com.pppp.mvicoreapp.main.model

import com.pppp.entities.ComicsBook
import io.reactivex.Single

interface Repository {
    fun getComics(): Single<List<ComicsBook>>

    fun getComicById(id: Int): Single<ComicsBook>
}