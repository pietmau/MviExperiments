package com.pppp.mvicoreapp.setup.main

import com.badoo.mvicore.binder.Binder
import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.main.view.MainBinding
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.usecases.main.MainFeature
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

class TestMainBinding(
    private val uiEventsConsumer: Consumer<MainUiEvent>? = null,
    private val viewModelsSource: ObservableSource<ComicsViewModel>? = null,
    private val newsSource: ObservableSource<MainFeature.News>? = null
) : MainBinding {
    private val binder: Binder

    init {
        binder = Binder()
    }

    override fun bind(
        viewModels: Consumer<ComicsViewModel>,
        uiEvents: Relay<MainUiEvent>,
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

class UiEventsConsumer : Relay<MainUiEvent>() {
    private var uiEvent: MainUiEvent? = null

    override fun hasObservers() = false

    override fun subscribeActual(observer: Observer<in MainUiEvent>?) {
    }

    override fun accept(uiEvent: MainUiEvent?) {
        this.uiEvent = uiEvent
    }
}

class NewsSource : ObservableSource<MainFeature.News> {
    val news = PublishSubject.create<MainFeature.News>()

    override fun subscribe(observer: Observer<in MainFeature.News>) {
        news.subscribe(observer)
    }
}
