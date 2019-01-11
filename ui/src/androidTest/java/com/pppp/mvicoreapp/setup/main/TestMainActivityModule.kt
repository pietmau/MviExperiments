package com.pppp.mvicoreapp.setup.main

import com.pppp.mvicoreapp.main.view.MainActivity

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [TestMainActivitySubcomponent::class])
internal abstract class TestMainActivityModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    internal abstract fun bindYourActivityInjectorFactory(builder: TestMainActivitySubcomponent.Builder):
            AndroidInjector.Factory<*>
}