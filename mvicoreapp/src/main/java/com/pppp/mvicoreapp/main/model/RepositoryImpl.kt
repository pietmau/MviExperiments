package com.pppp.mvicoreapp.main.model

import com.pppp.database.ComicsDatabase
import com.pppp.entities.Result
import com.pppp.network.model.ComicsApiClient
import com.pppp.network.model.networkchecker.NetworkChecker
import io.reactivex.Single

class RepositoryImpl(
    private val db: ComicsDatabase,
    private val api: ComicsApiClient,
    private val networkChecker: NetworkChecker
) :
    Repository {

    override val comics: Single<List<Result>> = getComicsInternal()

    private fun getComicsInternal(): Single<List<Result>> {
        val flatMap = Single.just(0).toObservable()
            .flatMap {
                if (networkChecker.networkIsAvailable()) {
                    api.comics.map { it.data?.results }
                        .map { it ?: emptyList() }
                        .doOnNext {
                                comics -> db.saveComics(comics) }
                        .flatMapIterable { it }
                } else {
                    db.getAllComics()
                }
            }
        return flatMap.toList()
    }
}