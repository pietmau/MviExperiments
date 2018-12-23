package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.detail.DetailActivity
import com.pppp.mvicoreapp.main.view.customview.MarvelRecyclerView
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun with(mviCoreModule: MviCoreModule): MviCoreComponent

    fun inject(marvelRecyclerView: MarvelRecyclerView)

    fun inject(marvelRecyclerView: DetailActivity)
}