package com.marvel.marvel.main.model


import com.marvel.marvel.main.model.pojos.Comics
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Api {

    @GET("v1/public/comics")
    fun getComics(@QueryMap query: Map<String, String>): Observable<Comics>
}
