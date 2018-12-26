package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}
