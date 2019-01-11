package com.pppp.mvicoreapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.pppp.mvicoreapp.application.App
import com.pppp.mvicoreapp.detail.view.DetailActivity
import com.pppp.mvicoreapp.detail.view.viewmodel.DetailViewModel
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel
import com.pppp.mvicoreapp.setup.app.DaggerTestAppComponent
import com.pppp.mvicoreapp.setup.app.TestAppModule
import com.pppp.mvicoreapp.setup.detail.DetailSource
import com.pppp.mvicoreapp.setup.detail.MockLoader
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class DetailActivityTest {
    protected lateinit var modelSource: DetailSource
    private val comics: ComicsBookViewModel = ComicsBookViewModel(title = TEXT)
    protected val detailViewModel = DetailViewModel.GotNewData(comics)

    @get:Rule
    val activityRule = object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
        override fun beforeActivityLaunched() {
            val instrumentation = InstrumentationRegistry.getInstrumentation()
            val app = instrumentation.targetContext.applicationContext as App
            val builder = DaggerTestAppComponent.builder()
            modelSource = DetailSource()
            val appModule = TestAppModule(detailModelSource = modelSource, imageLoader = MockLoader)
            val component = builder.testAppModule(appModule).build()
            app.component = component
            app.component.inject(app)
        }
    }

    @Test
    fun when_emitsModel_thenRightTextShows() {
        //  WHEN
        modelSource.viewModels.onNext(detailViewModel)
        // THEN
        onView(withId(R.id.title_tv)).check(matches(withText(TEXT)))
    }

    @Test
    fun when_error_then_showsSnack() {
        //  WHEN
        modelSource.viewModels.onNext(DetailViewModel.Error(TEXT))
        //  THEN
        onView(withText(TEXT)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    companion object {
        const val TEXT = "this_is_some_text"
    }
}