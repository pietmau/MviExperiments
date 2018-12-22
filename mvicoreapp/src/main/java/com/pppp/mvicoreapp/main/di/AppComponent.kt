package com.pppp.mvicoreapp.main.di

import com.marvel.marvel.customview.MarvelRecyclerView
import dagger.Component

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun with(mviCoreModule: MviCoreModule): MviCoreComponent

    fun inject(marvelRecyclerView: MarvelRecyclerView)
}