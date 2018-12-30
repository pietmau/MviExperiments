package com.pppp.mvicoreapp.main.model

import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pppp.database.ComicsDatabase
import com.pppp.entities.ComicsBook
import com.pppp.network.model.ComicsClient
import com.pppp.network.model.networkchecker.NetworkChecker
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryImplTest {
    @Mock
    lateinit var db: ComicsDatabase
    @Mock
    lateinit var api: ComicsClient
    @Mock
    lateinit var networkChecker: NetworkChecker
    @InjectMocks
    lateinit var repo: RepositoryImpl
    @Mock
    lateinit var book: ComicsBook
    private lateinit var comics: List<ComicsBook>

    @Before
    fun setUp() {
        comics = listOf(book)
        whenever(api.getComics()).thenReturn(Observable.just(comics))
        whenever(networkChecker.isNetworkAvailable()).thenReturn(Single.just(true))
    }

    @Test
    fun when_noNetwork_then_usesCache() {
        //GIVEN
        whenever(networkChecker.isNetworkAvailable()).thenReturn(Single.just(false))
        //WHEN
        test()
        //THEN
        verify(db).getAllComics()
        verify(api, never()).getComics()
    }

    @Test
    fun when_network_then_usesApi() {
        //WHEN
        test()
        //THEN
        verify(db, never()).getAllComics()
        verify(api).getComics()
    }

    @Test
    fun when_successful_then_savesResponse() {
        //WHEN
        test()
        //THEN
        verify(db).saveComics(comics)
    }

    private fun test() {
        repo.getComics().test()
    }
}