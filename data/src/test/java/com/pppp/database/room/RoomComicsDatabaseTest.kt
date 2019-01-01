package com.pppp.database.room

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.pppp.database.ComicsDatabase
import com.pppp.database.database.ComicsDao
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RoomComicsDatabaseTest {
    private lateinit var db: ComicsDatabase
    private lateinit var dao: ComicsDao

    @Before
    fun setUp() {
        dao = mock<ComicsDao>()
        db = RoomComicsDatabase(dao, Schedulers.trampoline())
    }

    @Test
    fun when_save_then_savesToDb() {
        // WHEN
        db.saveComics(emptyList())
        // THEn
        verify(dao).insertAll(emptyList())
    }

    @Test
    fun when_getById_then_getsFromDao() {
        // WHEN
        db.getComicById(ID).test()
        // THEn
        verify(dao).getBookById(ID)
    }

    @Test
    fun when_getAllComic_then_getsFromDao() {
        // WHEN
        db.getAllComics()
        // THEn
        verify(dao).getAllBooks()
    }

    companion object {
        private const val ID = 123456
    }
}