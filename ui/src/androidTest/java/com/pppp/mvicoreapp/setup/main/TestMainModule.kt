package com.pppp.mvicoreapp.setup.main

import com.jakewharton.rxrelay2.Relay
import com.pppp.features.main.MainFeature
import com.pppp.mvicoreapp.main.view.MainBinding
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.ObservableSource
@Module
class TestMainModule {

    @Provides
    fun mainBinding(
        eventsConsumer: Relay<MainUiEvent>?,
        viewModelsSource: ObservableSource<ComicsViewModel>?,
        newsSource: ObservableSource<MainFeature.News>?
    ): MainBinding =
        TestMainBinding(eventsConsumer, viewModelsSource, newsSource)
}
