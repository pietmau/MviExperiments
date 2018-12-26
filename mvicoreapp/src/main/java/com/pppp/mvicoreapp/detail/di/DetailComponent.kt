package com.pppp.mvicoreapp.detail.di

import com.pppp.mvicoreapp.detail.DetailActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(DetailModule::class))
interface DetailComponent {

    fun inject(detailActivity: DetailActivity)
}