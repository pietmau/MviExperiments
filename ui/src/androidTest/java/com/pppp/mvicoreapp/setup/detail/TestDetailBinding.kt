package com.pppp.mvicoreapp.setup.detail

import com.badoo.mvicore.binder.Binder
import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.detail.view.DetailBinding
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.viewmodel.DetailViewModel
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

class TestDetailBinding(
    private val viemodelSource: ObservableSource<DetailViewModel>,
    private val eventsConsumer: Relay<DetailUiEvent>
) : DetailBinding {
    private val binder = Binder()

    override fun bind(viewModels: Consumer<DetailViewModel>, detailUiEvents: Relay<DetailUiEvent>) {
        viemodelSource?.let { binder.bind(viemodelSource to viewModels) }
        eventsConsumer?.let { binder.bind(detailUiEvents to eventsConsumer) }
    }
}

class DetailSource : ObservableSource<DetailViewModel> {
    val viewModels = PublishSubject.create<DetailViewModel>()

    override fun subscribe(observer: Observer<in DetailViewModel>) {
        viewModels.observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}

class DetailConsumer : Relay<DetailUiEvent>() {
    private var uiEvent: DetailUiEvent? = null

    override fun hasObservers() = false

    override fun subscribeActual(observer: Observer<in DetailUiEvent>?) {
    }

    override fun accept(uiEvent: DetailUiEvent?) {
        this.uiEvent = uiEvent
    }
}