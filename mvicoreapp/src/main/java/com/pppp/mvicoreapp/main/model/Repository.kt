package com.pppp.mvicoreapp.main.model

import com.pppp.entities.Result
import io.reactivex.Single

interface Repository {
    fun getComics(): Single<List<Result>>

    fun getComicById(id: Int): Single<Result>
}