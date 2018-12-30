package com.pppp.mvicoreapp.main

import com.pppp.mvicoreapp.main.MainFeature.Effect.ShowDetail
import com.pppp.mvicoreapp.main.MainFeature.State.Starting
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FunctionsTest {

    @Test
    fun when_startedGettingComics_then_startedGettingComics() {
        val news = publishNews(MainFeature.Wish.GetComics, ShowDetail(ID.toString()), Starting)
        assertEquals(ID, (news as MainFeature.News.ShowDetail).id)
    }

    @Test
    fun when_exception_then_emitsError() {
        val news = publishNews(MainFeature.Wish.GetComics, ShowDetail("Error"), Starting)
        assertThat(news, instanceOf(MainFeature.News.Error::class.java))
    }

    @Test
    fun when_othercases_then_emitsNull() {
        val news = publishNews(MainFeature.Wish.GetComics,
            MainFeature.Effect.StartedGettingComics, Starting)
        assertNull(news)
    }

    companion object {
        private const val ID = 123456
    }
}