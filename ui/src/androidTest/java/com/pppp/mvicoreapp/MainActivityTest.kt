package com.pppp.mvicoreapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pppp.mvicoreapp.R.id
import com.pppp.mvicoreapp.main.view.customview.ComicsHolder
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.mvicoreapp.setup.UiTest
import org.hamcrest.Matchers.not
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest : UiTest() {

    @Test
    fun when_starts_then_progress_doesNotShow() {
        //GIVEN
        initiallyProgressDoesNotShow()
        // WHEN
        viewModelsSource.viewModels.onNext(ComicsViewModel.Starting)
        decrementIdlingResource()
        // THEN
        onView(withId(id.progress)).check(matches(not(isDisplayed())))
    }

    @Test
    fun when_isGettingData_then_progress_shows() {
        //GIVEN
        initiallyProgressDoesNotShow()
        // WHEN
        viewModelsSource.viewModels.onNext(ComicsViewModel.GettingComics)
        decrementIdlingResource()
        // THEN
        onView(withId(id.progress)).check(matches(isDisplayed()))
    }

    @Test
    fun when_gotComics_then_progress_disappears() {
        //GIVEN
        initiallyProgressDoesNotShow()
        // WHEN
        viewModelsSource.viewModels.onNext(ComicsViewModel.SuccessGettingComics(emptyList()))
        decrementIdlingResource()
        // THEN
        onView(withId(id.progress)).check(matches(not(isDisplayed())))
    }

    @Test
    fun when_error_then_showsSnack() {
        // WHEN
        incrementIdlingResource()
        viewModelsSource.viewModels.onNext(ComicsViewModel.Failure(ERROR))
        decrementIdlingResource()
        // THEN
        onView(withText(ERROR)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun when_gotComics_then_showsItems() {
        // WHEN
        successfullyGotData()
        // THEN
        onView(withId(id.recycler)).check(matches(hasDescendant(withId(id.image))))
    }

    @Test
    fun when_gotComics_then_showsTextInItem() {
        // WHEN
        successfullyGotData()
        // THEN
        onView(withId(id.recycler)).check(matches(hasDescendant(withText(TITLE))))
    }

    @Test //TODO migrate to AndroidX and complete
    fun when_clickOnItem_then_rigtNewsIsDelivered() {
        // WHEN
        successfullyGotData()
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<ComicsHolder>(0, click()))
        // THEN
        fail()
    }
}
