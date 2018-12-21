package com.pppp.mvicoreapp.di

import com.pppp.mvicoreapp.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MviCoreModule::class))
interface MviCoreComponent {

    fun inject(mainActivity: MainActivity)
}
