package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.main.view.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MviCoreModule::class))
interface MviCoreComponent {

    fun inject(mainActivity: MainActivity)
}
