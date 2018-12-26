package com.pppp.mvicoreapp.application.di

import android.app.Application
import com.marvel.marvel.main.model.RetrofitClient
import com.pppp.database.ComicsDatabase
import com.pppp.database.ComicsDatabaseImpl
import com.pppp.mvicoreapp.main.model.Repository
import com.pppp.mvicoreapp.main.model.RepositoryImpl
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import com.pppp.mvicoreapp.main.view.customview.PicassoImageLoader
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapperImp
import com.pppp.network.model.ComicsApiClient
import com.pppp.network.model.networkchecker.NetworkChecker
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideComicsBookMapper(): ComicsBookMapper =
        ComicsBookMapperImp(application.applicationContext)

    @Provides
    fun provideImageLoader(): ImageLoader = PicassoImageLoader

    @Provides
    fun provideApi(): ComicsApiClient = RetrofitClient(application.cacheDir)

    @Provides
    fun provideDatabase(): ComicsDatabase = ComicsDatabaseImpl(application)

    @Provides
    fun provideRepository(
        db: ComicsDatabase,
        client: ComicsApiClient
    ): Repository =
        RepositoryImpl(db, client, NetworkChecker.checker(application))

}