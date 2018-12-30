package com.pppp.mvicoreapp

import com.badoo.mvicore.binder.Binder
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.main.MainBinding
import com.pppp.mvicoreapp.main.MainFeature
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

class TestMainBinding(
    private val uiEventsConsumer: Consumer<UiEventTransformer.UiEvent>? = null,
    private val viewModelsSource: ObservableSource<ComicsViewModel>? = null,
    private val newsSource: ObservableSource<MainFeature.News>? = null
) : MainBinding {
    private val binder: Binder

    init {
        binder = Binder()
    }

    override fun bind(
        viewModels: Consumer<ComicsViewModel>,
        uiEvents: PublishRelay<UiEventTransformer.UiEvent>,
        news: Consumer<MainFeature.News>
    ) {
        viewModelsSource?.let { binder.bind(it to viewModels) }
        uiEventsConsumer?.let { binder.bind(uiEvents to it) }
        newsSource?.let { binder.bind(it to news) }
    }
}

class MainThreadViewModelsSource : ObservableSource<ComicsViewModel> {
    val viewModels = PublishSubject.create<ComicsViewModel>()

    override fun subscribe(observer: Observer<in ComicsViewModel>) {
        viewModels.observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}

class UiEventsConsumer : Consumer<UiEventTransformer.UiEvent> {
    private var uiEvent: UiEventTransformer.UiEvent? = null

    override fun accept(uiEvent: UiEventTransformer.UiEvent?) {
        this.uiEvent = uiEvent
    }
}

class NewsSource : ObservableSource<MainFeature.News> {
    val news = PublishSubject.create<MainFeature.News>()

    override fun subscribe(observer: Observer<in MainFeature.News>) {
        news.subscribe(observer)
    }
}
