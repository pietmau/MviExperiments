package com.pppp.mvicoreapp.di

import dagger.Component

@Component
interface AppComponent {

    fun with(mviCoreModule: MviCoreModule): MviCoreComponent
}