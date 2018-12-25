package com.pppp.network.model

import com.marvel.marvel.main.model.pojos.Comics
import io.reactivex.Observable

interface ComicsApiClient {

    val comics: Observable<Comics>
}
