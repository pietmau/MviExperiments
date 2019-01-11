package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.detail.view.DetailActivity
import com.pppp.mvicoreapp.setup.detail.TestDetailModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [TestDetailModule::class])
interface TestDetailActivitySubcomponent : AndroidInjector<DetailActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DetailActivity>()
}
