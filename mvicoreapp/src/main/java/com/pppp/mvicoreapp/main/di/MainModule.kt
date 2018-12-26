package com.pppp.mvicoreapp.main.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.main.MainActor
import com.pppp.mvicoreapp.main.MainFeature
import com.pppp.mvicoreapp.main.MviBinding
import com.pppp.mvicoreapp.main.MviBindingImpl
import com.pppp.mvicoreapp.main.model.Repository
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.mvicoreapp.main.view.viewmodel.ViewModelTransformer
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideBindings(feature: MainFeature, mapper: ComicsBookMapper): MviBinding =
        MviBindingImpl(
            activity,
            feature,
            ViewModelTransformer(mapper),
            UiEventTransformer()
        )

    @Provides
    fun provideRelay(): PublishRelay<UiEventTransformer.UiEvent> = PublishRelay.create()


    @Provides
    fun provideActor(repository: Repository) = MainActor(repository)

    @Provides
    fun provideFeature(factory: ViewModelProvider.Factory): MainFeature =
        ViewModelProviders.of(activity, factory).get(MviViewModel::class.java).mainFeature

    @Provides
    fun provideFactory(actor: MainActor): ViewModelProvider.Factory = Factory(actor)

    class MviViewModel(mainActor: MainActor) : ViewModel() {
        val mainFeature: MainFeature by lazy { MainFeature(mainActor) }
    }

    class Factory(private val mainActor: MainActor) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MviViewModel(mainActor) as T
        }
    }
}