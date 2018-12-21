package com.marvel.marvel.main.model


import com.marvel.marvel.main.model.pojos.Comics
import retrofit2.http.GET
import retrofit2.http.QueryMap
import rx.Observable

interface Api {

    val comics: Observable<Comics>

    @GET("v1/public/comics")
    fun getComics(@QueryMap query: Map<String, String>): Observable<Comics>
}
