package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.detail.di.DetailModule
import com.pppp.mvicoreapp.detail.view.DetailActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [DetailModule::class])
interface DetailActivitySubcomponent : AndroidInjector<DetailActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DetailActivity>()
}
