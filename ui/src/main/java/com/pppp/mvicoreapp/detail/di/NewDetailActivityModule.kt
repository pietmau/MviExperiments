package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.detail.view.DetailActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [NewDetailActivitySubcomponent::class])
internal abstract class NewDetailActivityModule {
    @Binds
    @IntoMap
    @ClassKey(DetailActivity::class)
    internal abstract fun bindYourActivityInjectorFactory(builder: NewDetailActivitySubcomponent.Builder): AndroidInjector.Factory<*>
}