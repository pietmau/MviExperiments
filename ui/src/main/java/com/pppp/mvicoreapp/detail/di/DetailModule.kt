package com.pppp.mvicoreapp.detail.di

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import com.pppp.features.detail.DetailActor
import com.pppp.features.detail.DetailFeature
import com.pppp.features.repository.Repository
import com.pppp.mvicoreapp.detail.view.DetailActivity
import com.pppp.mvicoreapp.detail.view.DetailBinding
import com.pppp.mvicoreapp.detail.view.DetailBindingImpl
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEventTransformerImpl
import com.pppp.mvicoreapp.detail.view.viewmodel.DetailViewModelTransformerImpl
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
open class DetailModule {

    @Provides
    open fun provideBindings(
        repository: Repository,
        mapper: ComicsBookMapper,
        activity: DetailActivity
    ): DetailBinding =
        DetailBindingImpl(
            activity,
            DetailFeature(
                DetailActor(
                    repository,
                    Schedulers.io(),
                    AndroidSchedulers.mainThread()
                )
            ),
            DetailViewModelTransformerImpl(mapper),
            DetailUiEventTransformerImpl()
        )

    @Provides
    fun provideRelay(): Relay<DetailUiEvent> = PublishRelay.create()
}
