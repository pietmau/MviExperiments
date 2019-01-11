package com.pppp.mvicoreapp.application.di

import com.pppp.database.RepositoryComponent
import com.pppp.mvicoreapp.main.di.YourActivityModule
import dagger.Component

@Component(
    modules = [AppModule::class, YourActivityModule::class],
    dependencies = [RepositoryComponent::class]
)
abstract class ProdAppComponent : AppComponent