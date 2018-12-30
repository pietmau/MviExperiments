package com.marvel.marvel.main.model


import com.pppp.entities.ComicsBook
import io.reactivex.Observable
import retrofit2.http.GET

internal interface Api {

    @GET("v1/public/comics")
    fun getComics(): Observable<List<ComicsBook>>
}
