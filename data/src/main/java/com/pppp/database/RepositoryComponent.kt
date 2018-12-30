package com.pppp.database

import com.pppp.usecases.repository.Repository
import dagger.Component

@Component(modules = [RepositoryModule::class])
abstract class RepositoryComponent {

    abstract fun repository(): Repository
}