package com.pppp.mvicoreapp.application

import android.app.Activity
import android.app.Application
import com.pppp.database.DaggerRepositoryComponent
import com.pppp.database.RepositoryModule
import com.pppp.mvicoreapp.application.di.AppComponent
import com.pppp.mvicoreapp.application.di.AppModule
import com.pppp.mvicoreapp.application.di.DaggerProdAppComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class App : Application(), HasActivityInjector {
    @Inject
    lateinit
    var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingActivityInjector

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        val builder = DaggerRepositoryComponent.builder()
        val repositoryComponent = builder.repositoryModule(RepositoryModule(this)).build()
        val appModule = AppModule(this)
        val appComponentBuilder = DaggerProdAppComponent.builder().appModule(appModule)
            .repositoryComponent(repositoryComponent)

        component = appComponentBuilder.build()
        component.inject(this)
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}