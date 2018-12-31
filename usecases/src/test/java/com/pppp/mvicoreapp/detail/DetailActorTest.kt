package com.pppp.mvicoreapp.detail

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pppp.usecases.detail.DetailActor
import com.pppp.usecases.detail.DetailFeature
import com.pppp.usecases.repository.Repository
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
    private val starting = DetailFeature.State.Starting
    @Mock
    lateinit var book: com.pppp.lib.ComicsBook

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