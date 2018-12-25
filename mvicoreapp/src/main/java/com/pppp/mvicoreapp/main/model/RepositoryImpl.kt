package com.pppp.mvicoreapp.main.model

import com.marvel.marvel.main.model.ComicsApiClient
import com.marvel.marvel.main.model.pojos.Comics
import com.pppp.database.database.ComicsDatabase
import io.reactivex.Observable

class RepositoryImpl(private val db: ComicsDatabase, private val api: ComicsApiClient) :
    Repository {

    override val comics: Observable<Comics> = TODO()
}