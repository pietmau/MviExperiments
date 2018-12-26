package com.pppp.mvicoreapp.application.di

import com.pppp.mvicoreapp.detail.di.DetailComponent
import com.pppp.mvicoreapp.detail.di.DetailModule
import com.pppp.mvicoreapp.main.di.MainComponent
import com.pppp.mvicoreapp.main.di.MainModule
import com.pppp.mvicoreapp.main.view.customview.MarvelRecyclerView
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun with(mainModule: MainModule): MainComponent

    fun with(detailModule: DetailModule): DetailComponent

    fun inject(marvelRecyclerView: MarvelRecyclerView)
}