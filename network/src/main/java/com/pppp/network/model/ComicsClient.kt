package com.pppp.network.model

import io.reactivex.Observable

interface ComicsClient {

    fun getComics(): Observable<List<com.pppp.lib.ComicsBook>>
}
