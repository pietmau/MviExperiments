package com.pppp.usecases.detail

import com.nhaarman.mockitokotlin2.whenever
import com.pppp.lib.ComicsBook
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DeatilReducerTest {
    @Mock
    lateinit var state: DetailFeature.State
    @Mock
    lateinit var gotBook: DetailFeature.Effect.GotBookDetail
    @Mock
    lateinit var error: DetailFeature.Effect.Error
    @Mock
    lateinit var book: ComicsBook
    @Mock
    lateinit var exception: Exception

    @Before
    fun setUp() {
        whenever(gotBook.comicsBook).thenReturn(book)
        whenever(error.exception).thenReturn(exception)
        whenever(exception.localizedMessage).thenReturn(TEXT)
    }

    @Test
    fun when_gotBook_then_emitsGot() {
        // WHEN
        val newState = reduceState(state, gotBook) as DetailFeature.State.GotData
        val comicsBook = newState.comicsBook
        // THEN
        assertEquals(book, comicsBook)
    }

    @Test
    fun when_gotError_then_emitsError() {
        // WHEN
        val newState = reduceState(state, error) as DetailFeature.State.Error
        val message = newState.errorMessage
        // THEN
        assertEquals(TEXT, message)
    }

    companion object {
        private const val TEXT = "this_is_a_text"
    }
}