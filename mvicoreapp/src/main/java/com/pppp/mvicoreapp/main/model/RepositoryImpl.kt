package com.pppp.mvicoreapp.main.model

import com.pppp.database.ComicsDatabase
import com.pppp.entities.ComicsBook
import com.pppp.network.model.ComicsApiClient
import com.pppp.network.model.networkchecker.NetworkChecker
import io.reactivex.Single

class RepositoryImpl(
    private val db: ComicsDatabase,
    private val api: ComicsApiClient,
    private val networkChecker: NetworkChecker
) :
    Repository {

    override fun getComics(): Single<List<ComicsBook>> {
        return networkChecker.isNetworkAvailable().toObservable()
            .flatMap { networkAvailable ->
                if (networkAvailable) {
                    api.comics.map { it.data?.results }
                        .map { it ?: emptyList() }
                        .doOnNext { comics -> db.saveComics(comics) }
                        .flatMapIterable { it }
                } else {
                    db.getAllComics()
                }
            }.toList()
    }

    override fun getComicById(id: Int): Single<ComicsBook> = db.getComicById(id)

}