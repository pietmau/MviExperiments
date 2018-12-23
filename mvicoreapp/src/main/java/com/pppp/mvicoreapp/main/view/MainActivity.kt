package com.pppp.mvicoreapp.main.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.detail.DetailActivity
import com.pppp.mvicoreapp.main.MviBinding
import com.pppp.mvicoreapp.main.di.Injector
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer.UiEvent
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer.UiEvent.ComicBookSelected
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel.*
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

// TODO make graph picture
class MainActivity : AppCompatActivity(), Consumer<ComicsViewModel> {
    @Inject
    lateinit var mviBinding: MviBinding
    private val uiEvents: PublishRelay<UiEvent> = PublishRelay.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Injector.inject(this)
        mviBinding.bind(this, uiEvents)
    }

    override fun accept(comicsViewModel: ComicsViewModel) {
        when (comicsViewModel) {
            Starting -> return
            GettingComics -> onGettingComics()
            is SuccessGettingComics -> onComicsAvailable(comicsViewModel)
            is ShowDetail-> startDetailActivity(comicsViewModel)
        }
    }

    private fun onComicsAvailable(viewModel: SuccessGettingComics) {
        progress.visibility = View.GONE
        recycler.onItemClick = { comicsBook, image ->
            uiEvents.accept(ComicBookSelected(comicsBook))
        }
        recycler.onComicsAvailable(viewModel.results)
    }

    private fun onGettingComics() {
        progress.visibility = View.VISIBLE
    }

    fun startDetailActivity(comicsViewModel: ShowDetail) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.COMICS_EXTRA, comicsViewModel)
        startActivity(intent)
    }

}
