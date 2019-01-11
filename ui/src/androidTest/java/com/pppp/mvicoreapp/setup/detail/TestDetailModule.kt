package com.pppp.mvicoreapp.setup.detail

import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.detail.view.DetailBinding
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.viewmodel.DetailViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.ObservableSource

@Module
class TestDetailModule {

    @Provides
    fun detailBinding(
        detailModelSource: ObservableSource<DetailViewModel>,
        detailEventsConsumer: Relay<DetailUiEvent>
    ): DetailBinding =
        TestDetailBinding(detailModelSource, detailEventsConsumer)
}
