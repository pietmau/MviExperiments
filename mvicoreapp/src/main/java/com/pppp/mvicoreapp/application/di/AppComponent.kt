package com.pppp.mvicoreapp.application.di

import com.pppp.mvicoreapp.detail.DetailActivity
import com.pppp.mvicoreapp.main.di.MainComponent
import com.pppp.mvicoreapp.main.di.MainModule
import com.pppp.mvicoreapp.main.view.customview.MarvelRecyclerView
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun with(mviCoreModule: MainModule): MainComponent

    fun inject(marvelRecyclerView: MarvelRecyclerView)

    fun inject(marvelRecyclerView: DetailActivity)
}