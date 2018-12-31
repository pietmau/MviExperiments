package com.pppp.mvicoreapp.detail.di

import com.pppp.mvicoreapp.detail.view.DetailActivity
import com.pppp.mvicoreapp.main.di.ActivityModule
import dagger.Subcomponent

@Subcomponent(modules = [DetailModule::class, ActivityModule::class])
interface DetailComponent {
    fun inject(detailActivity: DetailActivity)
}