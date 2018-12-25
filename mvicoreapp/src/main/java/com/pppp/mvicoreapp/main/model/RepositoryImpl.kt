package com.pppp.mvicoreapp.main.model

import com.marvel.marvel.main.model.ComicsApiClient
import com.marvel.marvel.main.model.pojos.Comics
import com.pppp.database.database.ComicsDatabase
import com.pppp.entities.Result
import io.reactivex.Observable

class RepositoryImpl(private val db: ComicsDatabase, private val api: ComicsApiClient) :
    Repository {

    override val comics: Observable<Comics>
        get() {
            val comics1 = api.comics.doOnNext { comics ->
                val resuts = comics.data?.results?.map { it as Result }//TODO fix
                db.dao().insert(resuts!!)
                val z = db.dao().getUsersWithRepos()
                var i =0
                i++
            }
            return comics1
        }
}