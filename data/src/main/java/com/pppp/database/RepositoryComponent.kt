package com.pppp.database

import com.pppp.features.repository.Repository
import dagger.Component

@Component(modules = [RepositoryModule::class])
abstract class RepositoryComponent {

    abstract fun repository(): Repository
}