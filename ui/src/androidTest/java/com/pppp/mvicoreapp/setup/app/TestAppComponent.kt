package com.pppp.mvicoreapp.setup.app

import com.pppp.mvicoreapp.application.di.AppComponent
import com.pppp.mvicoreapp.main.di.TestDetailActivityModule
import com.pppp.mvicoreapp.main.view.customview.ClickBlocker
import com.pppp.mvicoreapp.setup.main.TestMainActivityModule
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [TestAppModule::class, MocksModule::class, TestMainActivityModule::class,
        AndroidSupportInjectionModule::class, TestDetailActivityModule::class]
)
abstract class TestAppComponent : AppComponent

@Module
class MocksModule {

    @Provides
    fun clickBlocker(): ClickBlocker = InstantClickBlocker()
}
