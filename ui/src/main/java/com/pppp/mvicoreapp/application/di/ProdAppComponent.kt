package com.pppp.mvicoreapp.application.di

import com.pppp.database.RepositoryComponent
import dagger.Component

@Component(modules = [AppModule::class], dependencies = [RepositoryComponent::class])
abstract class ProdAppComponent : AppComponent