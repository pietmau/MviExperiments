package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.main.view.MainActivity

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(YourActivitySubcomponent::class))
internal abstract class YourActivityModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    internal abstract fun bindYourActivityInjectorFactory(builder: YourActivitySubcomponent.Builder): AndroidInjector.Factory<*>
}