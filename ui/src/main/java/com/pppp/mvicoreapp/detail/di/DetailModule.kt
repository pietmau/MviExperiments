package com.pppp.mvicoreapp.detail.di

import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.detail.view.DetailBinding
import com.pppp.mvicoreapp.detail.view.DetailBindingImpl
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEventTransformerImpl
import com.pppp.mvicoreapp.detail.view.viewmodel.DetailViewModelTransformerImpl
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.usecases.detail.Boot
import com.pppp.usecases.detail.DetailActor
import com.pppp.usecases.detail.DetailFeature
import com.pppp.usecases.repository.Repository
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
    ): DetailBinding =
        DetailBindingImpl(
            activity,
            DetailFeature(
                DetailActor(
                    repository,
                    Schedulers.io(),
                    AndroidSchedulers.mainThread()
                ),
                Boot(comicBookId)
            ),
            DetailViewModelTransformerImpl(mapper),
            DetailUiEventTransformerImpl()
        )

    @Provides
    fun provideRelay(): PublishRelay<DetailUiEvent> = PublishRelay.create()
}