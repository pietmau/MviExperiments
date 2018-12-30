package com.pppp.mvicoreapp.application

import android.app.Application
import com.pppp.mvicoreapp.application.di.AppComponent
import com.pppp.mvicoreapp.application.di.AppModule
import com.pppp.mvicoreapp.application.di.DaggerProdAppComponent
import com.squareup.leakcanary.LeakCanary

class App : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerProdAppComponent.builder().appModule(AppModule(this)).build()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}