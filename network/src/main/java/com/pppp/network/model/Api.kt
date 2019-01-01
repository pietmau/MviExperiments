package com.pppp.network.model

import com.pppp.lib.ComicsBook
import io.reactivex.Observable
import retrofit2.http.GET

internal interface Api {

    @GET("v1/public/comics")
    fun getComics(): Observable<List<ComicsBook>>
}
