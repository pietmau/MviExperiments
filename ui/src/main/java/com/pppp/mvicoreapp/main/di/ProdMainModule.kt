package com.pppp.mvicoreapp.main.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.main.view.MainBinding
import com.pppp.mvicoreapp.main.view.ProdMainBinding
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.uievent.MainUiEventTransformer
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.mvicoreapp.main.view.viewmodel.ViewModelTransformer
import com.pppp.features.main.MainActor
import com.pppp.features.main.MainFeature
import com.pppp.features.repository.Repository
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
open class ProdMainModule {

    @Provides
    fun provideBindings(
        feature: MainFeature,
        mapper: ComicsBookMapper,
        activity: AppCompatActivity
    ): MainBinding =
        ProdMainBinding(
            activity,
            feature,
            ViewModelTransformer(mapper),
            MainUiEventTransformer()
        )

    @Provides
    fun provideRelay(): Relay<MainUiEvent> = PublishRelay.create()

    @Provides
    fun provideActor(repository: Repository) =
        MainActor(
            repository,
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )

    @Provides
    fun provideFeature(
        factory: ViewModelProvider.Factory,
        activity: AppCompatActivity
    ): MainFeature =
        ViewModelProviders.of(activity, factory).get(MviViewModel::class.java).mainFeature

    @Provides
    fun provideFactory(actor: MainActor): ViewModelProvider.Factory =
        MviViewModel.Factory(actor)
}