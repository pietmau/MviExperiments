package com.pppp.database

import android.content.Context
import com.pppp.database.database.RoomDb
import com.pppp.database.repository.RepositoryImpl
import com.pppp.database.room.RoomComicsDatabase
import com.pppp.features.repository.Repository
import com.pppp.network.model.ComicsClient
import com.pppp.network.model.NetworkModule
import com.pppp.network.model.networkchecker.NetworkChecker
import dagger.Module
import dagger.Provides
import java.io.File

@Module(includes = [NetworkModule::class])
class RepositoryModule() {

    @Provides
    fun provideRepo(
        db: ComicsDatabase,
        api: ComicsClient,
        networkChecker: NetworkChecker
    ): Repository = RepositoryImpl(db, api, networkChecker)

    @Provides
    fun provideDb(context: Context): ComicsDatabase = RoomComicsDatabase(RoomDb.db(context).dao())

    @Provides
    fun provideCacheDir(context: Context): File = context.cacheDir
}