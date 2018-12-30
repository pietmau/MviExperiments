package com.pppp.mvicoreapp.setup

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.TestMainBinding
import com.pppp.mvicoreapp.main.MainActor
import com.pppp.mvicoreapp.main.MainBinding
import com.pppp.mvicoreapp.main.MainFeature
import com.pppp.mvicoreapp.main.di.MainModule
import com.pppp.mvicoreapp.main.model.Repository
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

@Module
class TestMainModule : MainModule {

    override fun provideBindings(
        feature: MainFeature,
        mapper: ComicsBookMapper,
        activity: AppCompatActivity
    ): MainBinding = TODO()

    @Provides
    fun mainBinding(
        uiEventsConsumer: Consumer<UiEventTransformer.UiEvent>?,
        viewModelsObservableSource: ObservableSource<ComicsViewModel>?,
        newsObservableSource: ObservableSource<MainFeature.News>?
    ): MainBinding =
        TestMainBinding(
            uiEventsConsumer,
            viewModelsObservableSource,
            newsObservableSource
        )

    @Provides
    override fun provideRelay(): PublishRelay<UiEventTransformer.UiEvent> = PublishRelay.create()

    @Provides
    override fun provideActor(repository: Repository) = MainActor(repository)

    @Provides
    override fun provideFeature(
        factory: ViewModelProvider.Factory,
        activity: AppCompatActivity
    ): MainFeature =
        ViewModelProviders.of(activity, factory).get(MviViewModel::class.java).mainFeature

    @Provides
    override fun provideFactory(actor: MainActor): ViewModelProvider.Factory = Factory(actor)

    class MviViewModel(mainActor: MainActor) : ViewModel() {
        val mainFeature: MainFeature by lazy { MainFeature(mainActor) }
    }

    class Factory(private val mainActor: MainActor) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MviViewModel(mainActor) as T
        }
    }
}

