package com.pppp.mvicoreapp.application.di

import android.app.Application
import com.marvel.marvel.main.model.RetrofitClient
import com.pppp.database.ComicsDatabase
import com.pppp.database.ComicsDatabaseImpl
import com.pppp.mvicoreapp.BuildConfig.*
import com.pppp.mvicoreapp.main.model.Repository
import com.pppp.mvicoreapp.main.model.RepositoryImpl
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import com.pppp.mvicoreapp.main.view.customview.PicassoImageLoader
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapperImp
import com.pppp.network.model.ComicsClient
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
    fun provideApi(): ComicsClient =
        RetrofitClient(application.cacheDir, PUBLIC_KEY, PRIVATE_KEY, MAIN_URL)

    @Provides
    fun provideDatabase(): ComicsDatabase = ComicsDatabaseImpl(application)

    @Provides
    fun provideRepository(
        db: ComicsDatabase,
        client: ComicsClient
    ): Repository =
        RepositoryImpl(db, client, NetworkChecker.checker(application))

}