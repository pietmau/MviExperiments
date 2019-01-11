package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.detail.view.DetailActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [DetailActivitySubcomponent::class])
internal abstract class DetailActivityModule {
    @Binds
    @IntoMap
    @ClassKey(DetailActivity::class)
    internal abstract fun bindYourActivityInjectorFactory(builder: DetailActivitySubcomponent.Builder): AndroidInjector.Factory<*>
}