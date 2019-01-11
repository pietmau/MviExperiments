package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.detail.view.DetailActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [TestDetailActivitySubcomponent::class])
internal abstract class TestDetailActivityModule {
    @Binds
    @IntoMap
    @ClassKey(DetailActivity::class)
    internal abstract fun bindYourActivityInjectorFactory(builder: TestDetailActivitySubcomponent.Builder): AndroidInjector.Factory<*>
}