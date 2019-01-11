package com.pppp.mvicoreapp.application.di

import com.pppp.mvicoreapp.application.App
import com.pppp.mvicoreapp.detail.di.DetailComponent
import com.pppp.mvicoreapp.main.di.ActivityModule
import com.pppp.mvicoreapp.main.di.MainComponent
import com.pppp.mvicoreapp.main.view.customview.MarvelRecyclerView

interface AppComponent {

    fun withMainComponent(module: ActivityModule): MainComponent

    fun withDetailComponent(module: ActivityModule): DetailComponent

    fun inject(marvelRecyclerView: MarvelRecyclerView)

    fun inject(app: App)
}