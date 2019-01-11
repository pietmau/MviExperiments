package com.pppp.mvicoreapp.application.di

import com.pppp.database.RepositoryComponent
import com.pppp.mvicoreapp.main.di.NewDetailActivityModule
import com.pppp.mvicoreapp.main.di.NewMainActivityModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AppModule::class, NewMainActivityModule::class, AndroidSupportInjectionModule::class, NewDetailActivityModule::class],
    dependencies = [RepositoryComponent::class]
)
abstract class ProdAppComponent : AppComponent