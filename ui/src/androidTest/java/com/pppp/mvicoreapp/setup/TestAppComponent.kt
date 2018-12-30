package com.pppp.mvicoreapp.setup

import com.pppp.mvicoreapp.application.di.AppComponent
import com.pppp.mvicoreapp.main.di.ActivityModule
import dagger.Component

@Component(modules = arrayOf(TestAppModule::class))
abstract class TestAppComponent : AppComponent {

    abstract override fun with(module: ActivityModule): TestMainComponent

}