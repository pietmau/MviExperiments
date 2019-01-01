package com.pppp.usecases.main

import com.nhaarman.mockitokotlin2.whenever
import com.pppp.lib.ComicsBook
import junit.framework.TestCase.assertEquals
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainReducerTest {
    @Mock
    lateinit var comicsRetrieved: MainFeature.Effect.ComicsRetrieved
    @Mock
    lateinit var state: MainFeature.State
    @Mock
    lateinit var book: List<ComicsBook>

    @Before
    fun setUp() {
        whenever(comicsRetrieved.comicsBooks).thenReturn(book)
    }

    @Test
    fun when_successGettingComics_then_successGettingComics() {
        // WHEN
        val newState = reduce(state, comicsRetrieved) as MainFeature.State.SuccessGettingComics
        val newBook = newState.comicsBooks
        // THEN
        assertEquals(this.book, newBook)
    }

    @Test
    fun when_gettingComics_then_gettingComics() {
        // WHEN
        val newState = reduce(state, MainFeature.Effect.StartedGettingComics)
        // THEN
        assertThat(newState, instanceOf(MainFeature.State.GettingComics::class.java))
    }
}