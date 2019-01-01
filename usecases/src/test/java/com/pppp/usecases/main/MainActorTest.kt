package com.pppp.usecases.main

import com.nhaarman.mockitokotlin2.whenever
import com.pppp.lib.ComicsBook
import com.pppp.usecases.main.MainFeature.Wish.ShowDetail
import com.pppp.usecases.repository.Repository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActorTest {
    @Mock
    lateinit var repository: Repository
    private val workerScheduler: Scheduler = Schedulers.trampoline()
    private val mainThreadSheduler: Scheduler = Schedulers.trampoline()
    private lateinit var actor: MainActor
    @Mock
    lateinit var comicsBook: ComicsBook
    lateinit var comics: List<ComicsBook>

    @Before
    fun setUp() {
        actor = MainActor(repository, workerScheduler, mainThreadSheduler)
        comics = listOf(comicsBook)
    }

    @Test
    fun when_starts_then_emitsStarting() {
        //  WHEN
        emit { emptyList() }
        //  THEN
        invokeActor().assertValueAt(0) { effect ->
            effect is MainFeature.Effect.StartedGettingComics
        }
    }

    @Test
    fun when_getsResult_then_emitsRetrieved() {
        //  WHEN
        emit { comics }
        //  THEN
        invokeActor().assertValueAt(1) { effect ->
            (effect as MainFeature.Effect.ComicsRetrieved).comicsBooks == comics
        }
    }

    @Test
    fun when_error_then_emitsFailure() {
        //  WHEN
        val exception = Exception(MESSAGE)
        emit { throw exception }
        //  THEN
        invokeActor().assertValueAt(1) { effect ->
            (effect as MainFeature.Effect.FailureRetrievingComics).error == exception
        }
    }

    @Test
    fun when_ShowDetail_then_emitsShowDetail() {
        //  WHEN
        val observable = actor.invoke(
            starting, ShowDetail(
                ID, 0))
        //  THEN
        observable.test().assertValueAt(0) { effect ->
            (effect as MainFeature.Effect.ShowDetail).id.equals(ID, true)
        }
    }

    private fun invokeActor() = actor.invoke(starting, getComics).test()

    private fun emit(function: () -> List<ComicsBook>) {
        val single = Single.fromCallable(function)
        whenever(repository.getComics()).thenReturn(single)
    }

    companion object {
        private val starting = MainFeature.State.Starting
        private val getComics = MainFeature.Wish.GetComics
        private const val MESSAGE = "this_is_a_message"
        private const val ID = "123456"
    }
}