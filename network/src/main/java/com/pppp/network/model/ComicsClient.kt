package com.pppp.network.model

import com.pppp.entities.ComicsBook
import io.reactivex.Observable

interface ComicsClient {

    fun getComics(): Observable<List<ComicsBook>>
}
