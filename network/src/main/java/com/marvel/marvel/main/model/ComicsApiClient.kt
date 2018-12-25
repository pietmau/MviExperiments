package com.marvel.marvel.main.model

import com.marvel.marvel.main.model.pojos.Comics
import io.reactivex.Observable

interface ComicsApiClient {

    val comics: Observable<Comics>
}
