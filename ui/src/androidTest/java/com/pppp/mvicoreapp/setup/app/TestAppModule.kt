package com.pppp.mvicoreapp.setup.app

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.viewmodel.DetailViewModel
import com.pppp.mvicoreapp.main.view.customview.ClickBlocker
import com.pppp.mvicoreapp.main.view.customview.Failure
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import com.pppp.mvicoreapp.main.view.customview.Success
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.mvicoreapp.setup.detail.DetailConsumer
import com.pppp.mvicoreapp.setup.detail.DetailSource
import com.pppp.mvicoreapp.setup.main.MainThreadViewModelsSource
import com.pppp.mvicoreapp.setup.main.NewsSource
import com.pppp.mvicoreapp.setup.main.UiEventsConsumer
import com.pppp.features.main.MainFeature
import dagger.Module
import dagger.Provides
import io.reactivex.ObservableSource

@Module
class TestAppModule(
    private val mainEventConsumer: Relay<MainUiEvent> = UiEventsConsumer(),
    private val mainModelSource: ObservableSource<ComicsViewModel> = MainThreadViewModelsSource(),
    private val mainNewsSource: ObservableSource<MainFeature.News> = NewsSource(),
    private val detailModelSource: ObservableSource<DetailViewModel> = DetailSource(),
    private val detailEventsConsumer: Relay<DetailUiEvent> = DetailConsumer(),
    val imageLoader: ImageLoader = AlwaysSuccessfulLoader()
) {

    @Provides
    fun viewModels() = mainModelSource

    @Provides
    fun uiEvents(): Relay<MainUiEvent> = mainEventConsumer

    @Provides
    fun news() = mainNewsSource

    @Provides
    fun relay(): Relay<DetailUiEvent> = detailEventsConsumer

    @Provides
    fun detailModelSource(): ObservableSource<DetailViewModel> = detailModelSource

    @Provides
    fun provideImageLoader(): ImageLoader = imageLoader
}

class InstantClickBlocker : ClickBlocker {
    override fun executeIfAppropriate(
        recycler: RecyclerView,
        position: Int,
        onItemClick: () -> Unit
    ) {
        onItemClick()
    }
}

class AlwaysSuccessfulLoader : ImageLoader {
    override fun cancelTask(image: ImageView) { /* NoOp */
    }

    override fun loadImage(view: ImageView, url: String?, success: Success?, failure: Failure?) {
        success?.invoke()
    }
}
