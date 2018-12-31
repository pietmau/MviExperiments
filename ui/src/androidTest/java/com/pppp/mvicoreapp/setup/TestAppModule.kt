package com.pppp.mvicoreapp.setup

import android.app.Application
import android.widget.ImageView
import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.main.view.customview.*
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapperImp
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.usecases.main.MainFeature
import com.pppp.usecases.repository.Repository
import dagger.Module
import dagger.Provides
import io.reactivex.ObservableSource

@Module
class TestAppModule(
    private val application: Application,
    private val uiEventsConsumer: Relay<MainUiEvent> = UiEventsConsumer(),
    private val viewModelsSource: ObservableSource<ComicsViewModel> = MainThreadViewModelsSource(),
    private val newsSource: ObservableSource<MainFeature.News> = NewsSource()
) {

    @Provides
    fun provideComicsBookMapper(): ComicsBookMapper =
        ComicsBookMapperImp(application.applicationContext)

    @Provides
    fun provideImageLoader(): ImageLoader = TestImageLoader()

    @Provides
    fun provideRepository(): Repository = TODO()

    @Provides
    fun viewModels() = viewModelsSource

    @Provides
    fun uiEvents(): Relay<MainUiEvent> = uiEventsConsumer

    @Provides
    fun news() = newsSource

    @Provides
    fun timer(): Timer = InstantTimer()

}

class InstantTimer : Timer {
    override fun waitOrExecute(
        comicsBook: ComicsBookViewModel,
        image: Int,
        onItemClick: OnItemClick?
    ) {
        onItemClick?.invoke(comicsBook, image)
    }

}

class TestImageLoader : ImageLoader {
    override fun cancelTask(image: ImageView) {

    }

    override fun loadImage(view: ImageView, url: String?, success: Success?, failure: Failure?) {
        success?.invoke()
    }

}

