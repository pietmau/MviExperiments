package com.pppp.mvicoreapp.application.di

import com.pppp.mvicoreapp.application.App
import com.pppp.mvicoreapp.main.view.customview.MarvelRecyclerView

interface AppComponent {

    fun inject(marvelRecyclerView: MarvelRecyclerView)

    fun inject(app: App)
}