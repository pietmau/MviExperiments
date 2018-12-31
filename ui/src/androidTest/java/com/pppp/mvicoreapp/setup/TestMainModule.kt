package com.pppp.mvicoreapp.setup

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.main.di.MainModule
import com.pppp.mvicoreapp.main.view.MainBinding
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.usecases.main.MainActor
import com.pppp.usecases.main.MainFeature
import com.pppp.usecases.repository.Repository
import dagger.Module
import dagger.Provides
import io.reactivex.ObservableSource
import io.reactivex.schedulers.Schedulers

@Module
class TestMainModule : MainModule {

    override fun provideBindings(
        feature: MainFeature,
        mapper: ComicsBookMapper,
        activity: AppCompatActivity
    ): MainBinding = TODO()

    @Provides
    fun mainBinding(
        uiEventsConsumer: Relay<MainUiEvent>?,
        viewModelsObservableSource: ObservableSource<ComicsViewModel>?,
        newsObservableSource: ObservableSource<MainFeature.News>?
    ): MainBinding =
        TestMainBinding(
            uiEventsConsumer,
            viewModelsObservableSource,
            newsObservableSource
        )

    @Provides
    override fun provideActor(repository: Repository) =
        MainActor(repository, Schedulers.trampoline(), Schedulers.trampoline())

    @Provides
    override fun provideFeature(
        factory: ViewModelProvider.Factory,
        activity: AppCompatActivity
    ): MainFeature =
        ViewModelProviders.of(activity, factory).get(MviViewModel::class.java).mainFeature

    @Provides
    override fun provideFactory(actor: MainActor): ViewModelProvider.Factory = Factory(actor)

    class MviViewModel(mainActor: MainActor) : ViewModel() {
        val mainFeature: MainFeature by lazy {
            MainFeature(
                mainActor
            )
        }
    }

    class Factory(private val mainActor: MainActor) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MviViewModel(mainActor) as T
        }
    }
}

