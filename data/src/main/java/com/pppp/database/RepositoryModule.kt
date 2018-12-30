package com.pppp.database

import android.content.Context
import com.pppp.database.repository.RepositoryImpl
import com.pppp.database.room.RoomComicsDatabase
import com.pppp.network.model.ComicsClient
import com.pppp.network.model.NetworkModule
import com.pppp.network.model.networkchecker.NetworkChecker
import com.pppp.usecases.repository.Repository
import dagger.Module
import dagger.Provides
import java.io.File

@Module(includes = [NetworkModule::class])
class RepositoryModule(private val context: Context) {

    @Provides
    fun provideContext() = context.applicationContext

    @Provides
    fun provideRepo(
        db: ComicsDatabase,
        api: ComicsClient,
        networkChecker: NetworkChecker
    ): Repository = RepositoryImpl(db, api, networkChecker)

    @Provides
    fun provideDb(): ComicsDatabase = RoomComicsDatabase(context)


    @Provides
    fun provideCacheDir(): File = context.cacheDir
}