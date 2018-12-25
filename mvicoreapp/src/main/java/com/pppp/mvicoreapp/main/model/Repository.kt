package com.pppp.mvicoreapp.main.model

import com.marvel.marvel.main.model.pojos.Comics
import io.reactivex.Observable

interface Repository {
    val comics: Observable<Comics>
}