package com.pppp.mvicoreapp.main.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.pppp.mvicoreapp.main.binders.MviBinding
import com.pppp.mvicoreapp.main.binders.MviBindingImpl
import com.pppp.mvicoreapp.main.MainFeature
import com.pppp.mvicoreapp.main.view.ViewModelTrasformer
import dagger.Module
import dagger.Provides

@Module
class MviCoreModule(private val appCompatActivity: AppCompatActivity) {

    @Provides
    fun provideBindings(feature: MainFeature): MviBinding =
        MviBindingImpl(appCompatActivity, feature,
            ViewModelTrasformer
        )

    @Provides
    fun provideFeature(): MainFeature =
        ViewModelProviders.of(appCompatActivity).get(MviViewModel::class.java).mainFeature


    class MviViewModel : ViewModel() {
        val mainFeature: MainFeature by lazy { MainFeature() }
    }
}