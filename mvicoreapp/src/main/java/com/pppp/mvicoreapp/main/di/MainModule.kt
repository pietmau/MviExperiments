package com.pppp.mvicoreapp.main.di

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.main.MainActor
import com.pppp.mvicoreapp.main.MainFeature
import com.pppp.mvicoreapp.main.MainBinding
import com.pppp.mvicoreapp.main.model.Repository
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper

interface MainModule {

    fun provideBindings(
        feature: MainFeature,
        mapper: ComicsBookMapper,
        activity: AppCompatActivity
    ): MainBinding

    fun provideRelay(): PublishRelay<UiEventTransformer.UiEvent>

    fun provideActor(repository: Repository): MainActor

    fun provideFeature(
        factory: ViewModelProvider.Factory,
        activity: AppCompatActivity
    ): MainFeature

    fun provideFactory(actor: MainActor): ViewModelProvider.Factory
}