package com.pppp.network.model

import io.reactivex.Observable
import retrofit2.http.GET

internal interface Api {

    @GET("v1/public/comics")
    fun getComics(): Observable<List<com.pppp.lib.ComicsBook>>
}
