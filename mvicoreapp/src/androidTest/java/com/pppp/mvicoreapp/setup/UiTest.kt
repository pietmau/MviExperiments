package com.pppp.mvicoreapp.setup

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.application.App
import com.pppp.mvicoreapp.main.MainActivity
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModelImpl
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class UiTest {
    protected lateinit var viewModelsSource: MainThreadViewModelsSource

    protected lateinit var idling: CountingIdlingResource

    @get:Rule
    val activityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun beforeActivityLaunched() {
            val instrumentation = InstrumentationRegistry.getInstrumentation()
            val app = instrumentation.targetContext.applicationContext as App
            val builder = DaggerTestAppComponent.builder()
            viewModelsSource = MainThreadViewModelsSource()
            val testComponent =
                builder.testAppModule(TestAppModule(app, viewModelsSource = viewModelsSource))
                    .build()
            app.component = testComponent
        }
    }

    @Before
    fun setUp() {
        idling = CountingIdlingResource("Events", true)
        registerIdlingResource()
    }

    @After
    internal fun tearDown() {
        IdlingRegistry.getInstance().unregister(idling)
    }

    protected fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(idling)
    }

    protected fun decrementIdlingResource() {
        idling.decrement()
    }

    protected fun incrementIdlingResource() {
        idling.increment()
    }

    protected fun initiallyProgressDoesNotShow() {
        Espresso.onView(ViewMatchers.withId(R.id.progress))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        incrementIdlingResource()
    }

    protected fun successfullyGotData() {
        incrementIdlingResource()
        viewModelsSource.viewModels.onNext(ComicsViewModel.SuccessGettingComics(getList()))
        decrementIdlingResource()
    }

    private fun getList(): List<ComicsBookViewModel> =
        listOf(ComicsBookViewModelImpl(title = TITLE, imageUrl = URL))

    companion object {
        const val ERROR = "this_is_an_error"
        const val TITLE = "this_is_a_title"
        const val URL = "this_is_a_url"
    }
}