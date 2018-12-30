package com.pppp.mvicoreapp.detail.di

import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.detail.*
import com.pppp.mvicoreapp.detail.view.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.DetailViewModelTransformerImpl
import com.pppp.mvicoreapp.main.model.Repository
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class DetailModule(private val activity: AppCompatActivity, private val comicBookId: Int) {

    @Provides
    fun provideBindings(
        repository: Repository,
        mapper: ComicsBookMapper
    ): DetailBinding = DetailBindingImpl(
        activity,
        DetailFeature(
            DetailActor(repository, Schedulers.io(), AndroidSchedulers.mainThread()),
            Boot(comicBookId)
        ),
        DetailViewModelTransformerImpl(mapper),
        ::transformUiEvent
    )

    @Provides
    fun provideRelay(): PublishRelay<DetailUiEvent> = PublishRelay.create()
}