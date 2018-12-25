package com.pppp.mvicoreapp.main.model

import com.pppp.entities.Result
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {
    val comics: Single<List<Result>>
}