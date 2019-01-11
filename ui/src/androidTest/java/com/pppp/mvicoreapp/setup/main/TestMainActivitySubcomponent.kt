package com.pppp.mvicoreapp.setup.main

import com.pppp.mvicoreapp.main.view.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [TestMainModule::class])
interface TestMainActivitySubcomponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
