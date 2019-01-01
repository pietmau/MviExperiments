package com.pppp.network.model

import com.pppp.lib.ComicsBook
import io.reactivex.Observable

interface ComicsClient {

    fun getComics(): Observable<List<ComicsBook>>
}
