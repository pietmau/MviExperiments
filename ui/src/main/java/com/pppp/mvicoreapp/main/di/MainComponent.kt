package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.main.view.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [ProdMainModule::class, ActivityModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}
