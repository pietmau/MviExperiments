package com.pppp.mvicoreapp.application.di

import com.pppp.mvicoreapp.detail.di.DetailComponent
import com.pppp.mvicoreapp.detail.di.DetailModule
import com.pppp.mvicoreapp.main.di.ActivityModule
import com.pppp.mvicoreapp.main.di.MainComponent
import com.pppp.mvicoreapp.main.view.customview.MarvelRecyclerView

interface AppComponent {

    fun with(module: ActivityModule): MainComponent

    fun with(detailModule: DetailModule): DetailComponent

    fun inject(marvelRecyclerView: MarvelRecyclerView)
}