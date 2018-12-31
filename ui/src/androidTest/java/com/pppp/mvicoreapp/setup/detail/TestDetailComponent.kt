package com.pppp.mvicoreapp.setup.detail

import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.detail.di.DetailComponent
import com.pppp.mvicoreapp.detail.view.DetailBinding
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.viewmodel.DetailViewModel
import com.pppp.mvicoreapp.main.di.ActivityModule
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.reactivex.ObservableSource

@Subcomponent(modules = [TestDetailModule::class, ActivityModule::class])
abstract class TestDetailComponent : DetailComponent

@Module
class TestDetailModule {

    @Provides
    fun detailBinding(
        detailModelSource: ObservableSource<DetailViewModel>,
        detailEventsConsumer: Relay<DetailUiEvent>
    ): DetailBinding =
        TestDetailBinding(detailModelSource, detailEventsConsumer)
}
