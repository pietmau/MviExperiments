package com.pppp.mvicoreapp.application

import android.app.Application
import com.pppp.database.DaggerRepositoryComponent
import com.pppp.database.RepositoryModule
import com.pppp.mvicoreapp.application.di.AppComponent
import com.pppp.mvicoreapp.application.di.AppModule
import com.pppp.mvicoreapp.application.di.DaggerProdAppComponent
import com.squareup.leakcanary.LeakCanary

class App : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        val builder = DaggerRepositoryComponent.builder()
        val repositoryComponent = builder.repositoryModule(RepositoryModule(this)).build()
        val appModule = AppModule(this)
        val appComponentBuilder = DaggerProdAppComponent.builder().appModule(appModule)
            .repositoryComponent(repositoryComponent)

        component = appComponentBuilder.build()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}