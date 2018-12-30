package com.pppp.mvicoreapp.main.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.main.view.MainBinding
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.usecases.main.MainActor
import com.pppp.usecases.main.MainFeature
import com.pppp.usecases.repository.Repository

interface MainModule {

    fun provideBindings(
        feature: MainFeature,
        mapper: ComicsBookMapper,
        activity: AppCompatActivity
    ): MainBinding

    fun provideRelay(): PublishRelay<MainUiEvent>

    fun provideActor(repository: Repository): MainActor

    fun provideFeature(
        factory: ViewModelProvider.Factory,
        activity: AppCompatActivity
    ): MainFeature

    fun provideFactory(actor: MainActor): ViewModelProvider.Factory
}