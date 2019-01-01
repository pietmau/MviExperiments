package com.pppp.features.detail

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pppp.lib.ComicsBook
import com.pppp.features.repository.Repository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailActorTest {
    @Mock
    lateinit var repository: Repository
    private val workerScheduler: Scheduler = Schedulers.trampoline()
    private val mainThreadScheduler: Scheduler = Schedulers.trampoline()
    lateinit var detailActor: DetailActor
    @Mock
    lateinit var starting: DetailFeature.State
    @Mock
    lateinit var book: ComicsBook

    @Before
    fun setUp() {
        detailActor = DetailActor(repository, workerScheduler, mainThreadScheduler)
        whenever(repository.getComicById(anyInt())).thenReturn(Single.just(book))
    }

    @Test
    fun when_called_then_queriesTheDb() {
        // WHEN
        detailActor(starting, DetailFeature.Wish.GetBookDetail(ID))
            .test()
        // THEN
        verify(repository).getComicById(ID)
    }

    @Test
    fun when_called_then_returnsTheRightEffect() {
        detailActor(starting, DetailFeature.Wish.GetBookDetail(ID))
            .test().assertValueAt(0) { effect ->
                (effect as DetailFeature.Effect.GotBookDetail).comicsBook == book
            }
    }

    companion object {
        private const val ID = 123456
    }
}