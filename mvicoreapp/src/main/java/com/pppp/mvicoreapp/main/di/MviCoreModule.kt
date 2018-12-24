package com.pppp.mvicoreapp.main.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.marvel.marvel.main.model.NetworkService
import com.marvel.marvel.main.model.NetworkServiceRetrofit
import com.pppp.mvicoreapp.main.MainActor
import com.pppp.mvicoreapp.main.MainFeature
import com.pppp.mvicoreapp.main.MviBinding
import com.pppp.mvicoreapp.main.MviBindingImpl
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapperImp
import com.pppp.mvicoreapp.main.view.viewmodel.ViewModelTransformer
import dagger.Module
import dagger.Provides

@Module
class MviCoreModule(private val appCompatActivity: AppCompatActivity) {

    @Provides
    fun provideBindings(feature: MainFeature, mapper: ComicsBookMapper): MviBinding =
        MviBindingImpl(
            appCompatActivity,
            feature,
            ViewModelTransformer(mapper),
            UiEventTransformer()
        )

    @Provides
    fun provideComicsBookMapper(): ComicsBookMapper =
        ComicsBookMapperImp(appCompatActivity.applicationContext)

    @Provides
    fun provideActor(repository: NetworkService) = MainActor(repository)

    @Provides
    fun provideActorNetworkService(): NetworkService =
        NetworkServiceRetrofit(appCompatActivity.cacheDir)

    @Provides
    fun provideFeature(factory: ViewModelProvider.Factory): MainFeature =
        ViewModelProviders.of(appCompatActivity, factory).get(MviViewModel::class.java).mainFeature

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