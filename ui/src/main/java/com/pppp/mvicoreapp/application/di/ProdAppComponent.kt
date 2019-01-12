package com.pppp.mvicoreapp.application.di

import com.pppp.database.RepositoryModule
import com.pppp.mvicoreapp.main.di.DetailActivityModule
import com.pppp.mvicoreapp.main.di.MainActivityModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AppModule::class, MainActivityModule::class, AndroidSupportInjectionModule::class,
        DetailActivityModule::class, RepositoryModule::class]
)
abstract class ProdAppComponent : AppComponent