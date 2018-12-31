package com.pppp.mvicoreapp.setup.main

import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.main.di.ActivityModule
import com.pppp.mvicoreapp.main.di.MainComponent
import com.pppp.mvicoreapp.main.view.MainBinding
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.usecases.main.MainFeature
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.reactivex.ObservableSource

@Subcomponent(modules = [TestMainModule::class, ActivityModule::class])
abstract class TestMainComponent : MainComponent

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
