package com.pppp.mvicoreapp.application.di

import com.pppp.database.RepositoryComponent
import com.pppp.mvicoreapp.main.di.DetailActivityModule
import com.pppp.mvicoreapp.main.di.MainActivityModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AppModule::class, MainActivityModule::class, AndroidSupportInjectionModule::class,
        DetailActivityModule::class],
    dependencies = [RepositoryComponent::class]
)
abstract class ProdAppComponent : AppComponent