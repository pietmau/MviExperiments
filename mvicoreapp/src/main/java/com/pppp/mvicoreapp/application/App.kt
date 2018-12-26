package com.pppp.mvicoreapp.application

import android.app.Application
import com.pppp.mvicoreapp.application.di.AppComponent
import com.pppp.mvicoreapp.application.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary

class App : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().build()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}