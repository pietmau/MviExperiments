package com.pppp.mvicoreapp.setup

import android.app.Application
import com.marvel.marvel.main.model.RetrofitClient
import com.pppp.database.ComicsDatabase
import com.pppp.database.ComicsDatabaseImpl
import com.pppp.mvicoreapp.main.MainFeature
import com.pppp.mvicoreapp.main.model.Repository
import com.pppp.mvicoreapp.main.model.RepositoryImpl
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import com.pppp.mvicoreapp.main.view.customview.PicassoImageLoader
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapperImp
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.network.model.ComicsClient
import com.pppp.network.model.networkchecker.NetworkChecker
import dagger.Module
import dagger.Provides
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

@Module
class TestAppModule(
    private val application: Application,
    private val uiEventsConsumer: Consumer<UiEventTransformer.UiEvent> = UiEventsConsumer(),
    private val viewModelsSource: ObservableSource<ComicsViewModel> = MainThreadViewModelsSource(),
    private val newsSource: ObservableSource<MainFeature.News> = NewsSource()
) {

    @Provides
    fun provideComicsBookMapper(): ComicsBookMapper =
        ComicsBookMapperImp(application.applicationContext)

    @Provides
    fun provideImageLoader(): ImageLoader = PicassoImageLoader

    @Provides
    fun provideApi(): ComicsClient = RetrofitClient(application.cacheDir)

    @Provides
    fun provideDatabase(): ComicsDatabase = ComicsDatabaseImpl(application)

    @Provides
    fun provideRepository(
        db: ComicsDatabase,
        client: ComicsClient
    ): Repository =
        RepositoryImpl(db, client, NetworkChecker.checker(application))

    @Provides
    fun viewModels() = viewModelsSource

    @Provides
    fun uiEvents() = uiEventsConsumer

    @Provides
    fun news() = newsSource

}