package com.pppp.mvicoreapp.main.model

import com.pppp.database.ComicsDatabase
import com.pppp.network.model.ComicsApiClient
import com.pppp.network.model.networkchecker.NetworkChecker
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class RepositoryImplTest {
    @Inject
    lateinit var db: ComicsDatabase
    @Inject
    lateinit var api: ComicsApiClient
    @Inject
    lateinit var networkChecker: NetworkChecker
    @InjectMocks
    lateinit var repos: RepositoryImpl

    @Before
    fun setUp() {
        //whenever(api.)
    }

    @Test
    fun when_foo_then_bar() {

    }
}