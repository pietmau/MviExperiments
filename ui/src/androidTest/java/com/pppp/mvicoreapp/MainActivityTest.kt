package com.pppp.mvicoreapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pppp.mvicoreapp.R.id
import com.pppp.mvicoreapp.main.view.customview.ComicsHolder
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.mvicoreapp.setup.main.UiTest
import junit.framework.TestCase.assertEquals
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : UiTest() {

    @Test
    fun when_starts_then_progress_doesNotShow() {
        // GIVEN
        initiallyProgressDoesNotShow()
        //  WHEN
        viewModes.viewModels.onNext(ComicsViewModel.Starting)
        decrementIdlingResource()
        //  THEN
        onView(withId(id.progress)).check(matches(not(isDisplayed())))
    }

    @Test
    fun when_isGettingData_then_progress_shows() {
        // GIVEN
        initiallyProgressDoesNotShow()
        //  WHEN
        viewModes.viewModels.onNext(ComicsViewModel.GettingComics)
        decrementIdlingResource()
        //  THEN
        onView(withId(id.progress)).check(matches(isDisplayed()))
    }

    @Test
    fun when_gotComics_then_progress_disappears() {
        // GIVEN
        initiallyProgressDoesNotShow()
        //  WHEN
        viewModes.viewModels.onNext(ComicsViewModel.SuccessGettingComics(emptyList()))
        decrementIdlingResource()
        //  THEN
        onView(withId(id.progress)).check(matches(not(isDisplayed())))
    }

    @Test
    fun when_error_then_showsSnack() {
        //  WHEN
        incrementIdlingResource()
        viewModes.viewModels.onNext(ComicsViewModel.Failure(ERROR))
        decrementIdlingResource()
        //  THEN
        onView(withText(ERROR)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun when_gotComics_then_showsItems() {
        //  WHEN
        successfullyGotData()
        //  THEN
        onView(withId(id.recycler)).check(matches(hasDescendant(withId(id.image))))
    }

    @Test
    fun when_gotComics_then_showsTextInItem() {
        //  WHEN
        successfullyGotData()
        //  THEN
        onView(withId(id.recycler)).check(matches(hasDescendant(withText(TITLE))))
    }

    @Test
    fun when_clickOnItem_then_rigtNewsUiEventIsDelivered() {
        //  WHEN
        successfullyGotData()
        onView(withId(R.id.recycler)).perform(actionOnItemAtPosition<ComicsHolder>(0, click()))
        // THEN
        val comicBookSelected = consumerSpy.event as MainUiEvent.ComicBookSelected
        assertEquals(comicBookSelected.bookId, ID)
        assertEquals(comicBookSelected.position, 0)
    }
}
