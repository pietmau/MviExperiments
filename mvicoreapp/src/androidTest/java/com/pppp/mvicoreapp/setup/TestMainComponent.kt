package com.pppp.mvicoreapp.setup

import com.pppp.mvicoreapp.main.di.ActivityModule
import com.pppp.mvicoreapp.main.di.MainComponent
import dagger.Subcomponent

@Subcomponent(modules = [TestMainModule::class, ActivityModule::class])
abstract class TestMainComponent : MainComponent
