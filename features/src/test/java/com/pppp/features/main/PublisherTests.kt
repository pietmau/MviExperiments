package com.pppp.features.main

import com.pppp.features.main.MainFeature.Effect.ShowDetail
import com.pppp.features.main.MainFeature.State.Starting
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PublisherTests {
    @Mock
    lateinit var wish: MainFeature.Wish

    @Test
    fun when_startedGettingComics_then_startedGettingComics() {
        val news = publishNews(wish, ShowDetail(ID.toString(), 0), Starting)
        assertEquals(ID, (news as MainFeature.News.ShowDetail).id)
    }

    @Test
    fun when_exception_then_emitsError() {
        val news = publishNews(wish, ShowDetail("Error", 0), Starting)
        assertThat(news, instanceOf(MainFeature.News.Error::class.java))
    }

    @Test
    fun when_othercases_then_emitsNull() {
        val news = publishNews(wish, MainFeature.Effect.StartedGettingComics, Starting)
        assertNull(news)
    }

    companion object {
        private const val ID = 123456
    }
}