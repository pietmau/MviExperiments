package com.pppp.mvicoreapp.setup

import android.app.Application
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import com.pppp.mvicoreapp.main.view.customview.PicassoImageLoader
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapperImp
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.usecases.main.MainFeature
import com.pppp.usecases.repository.Repository
import dagger.Module
import dagger.Provides
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

@Module
class TestAppModule(
    private val application: Application,
    private val uiEventsConsumer: Consumer<MainUiEvent> = UiEventsConsumer(),
    private val viewModelsSource: ObservableSource<ComicsViewModel> = MainThreadViewModelsSource(),
    private val newsSource: ObservableSource<MainFeature.News> = NewsSource()
) {

    @Provides
    fun provideComicsBookMapper(): ComicsBookMapper =
        ComicsBookMapperImp(application.applicationContext)

    @Provides
    fun provideImageLoader(): ImageLoader = PicassoImageLoader

    @Provides
    fun provideRepository(): Repository = TODO()

    @Provides
    fun viewModels() = viewModelsSource

    @Provides
    fun uiEvents() = uiEventsConsumer

    @Provides
    fun news() = newsSource

}

