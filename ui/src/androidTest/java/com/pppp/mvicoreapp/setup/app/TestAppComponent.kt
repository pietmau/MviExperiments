package com.pppp.mvicoreapp.setup.app

import com.pppp.mvicoreapp.application.di.AppComponent
import com.pppp.mvicoreapp.main.di.ActivityModule
import com.pppp.mvicoreapp.main.view.customview.ClickBlocker
import com.pppp.mvicoreapp.setup.detail.TestDetailComponent
import com.pppp.mvicoreapp.setup.main.TestMainComponent
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [TestAppModule::class, MocksModule::class])
abstract class TestAppComponent : AppComponent {

    abstract override fun withMainComponent(module: ActivityModule): TestMainComponent

    abstract override fun withDetailComponent(activityModule: ActivityModule): TestDetailComponent
}

@Module
class MocksModule {

    @Provides
    fun clickBlocker(): ClickBlocker = InstantClickBlocker()
}
