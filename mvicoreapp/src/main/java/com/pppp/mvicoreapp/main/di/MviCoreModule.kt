package com.pppp.mvicoreapp.main.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
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
    fun provideBindings(
        feature: MainFeature,
        mapper: ComicsBookMapper
    ): MviBinding = MviBindingImpl(
        appCompatActivity,
        feature,
        ViewModelTransformer(mapper),
        UiEventTransformer()
    )

    @Provides
    fun provideComicsBookMapper(): ComicsBookMapper =
        ComicsBookMapperImp(appCompatActivity.applicationContext)

    @Provides
    fun provideFeature(): MainFeature =
        ViewModelProviders.of(appCompatActivity).get(MviViewModel::class.java).mainFeature


    private class MviViewModel : ViewModel() {
        val mainFeature: MainFeature by lazy { MainFeature() }
    }
}