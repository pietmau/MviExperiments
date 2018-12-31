package com.pppp.mvicoreapp.setup

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.application.App
import com.pppp.mvicoreapp.main.view.MainActivity
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import io.reactivex.Observer
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule


open class UiTest {
    protected lateinit var viewModes: MainThreadViewModelsSource
    protected lateinit var consumerSpy: ConsumerSpy
    protected lateinit var idling: CountingIdlingResource

    @get:Rule
    val activityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun beforeActivityLaunched() {
            val instrumentation = InstrumentationRegistry.getInstrumentation()
            val app = instrumentation.targetContext.applicationContext as App
            val builder = DaggerTestAppComponent.builder()
            consumerSpy = ConsumerSpy()
            viewModes = MainThreadViewModelsSource()
            val testAppModule =
                TestAppModule(app, viewModelsSource = viewModes, uiEventsConsumer = consumerSpy)
            val testComponent = builder.testAppModule(testAppModule).build()
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
        viewModes.viewModels.onNext(ComicsViewModel.SuccessGettingComics(getList()))
        decrementIdlingResource()
    }

    private fun getList(): List<ComicsBookViewModel> =
        listOf(ComicsBookViewModel(id = ID, title = TITLE, imageUrl = URL))

    companion object {
        const val ERROR = "this_is_an_error"
        const val TITLE = "this_is_a_title"
        const val URL = "this_is_a_url"
        const val ID = "123456"
    }

    class ConsumerSpy : Relay<MainUiEvent>() {
        var event: MainUiEvent? = null

        override fun accept(mainUiEvent: MainUiEvent) {
            event = mainUiEvent
        }

        override fun hasObservers() =false

        override fun subscribeActual(observer: Observer<in MainUiEvent>?) {

        }


    }
}