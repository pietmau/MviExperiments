package com.pppp.mvicoreapp.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_LONG
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.application.Injector
import com.pppp.mvicoreapp.detail.DetailActivity
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer.UiEvent
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer.UiEvent.ComicBookSelected
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel.SuccessGettingComics
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Consumer<ComicsViewModel> {
    @Inject
    lateinit var mainBinding: MainBinding
    @Inject
    lateinit var uiEvents: PublishRelay<UiEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Injector.inject(this)
        mainBinding.bind(this, uiEvents, Consumer<MainFeature.News> { news ->
            when (news) {
                is MainFeature.News.ShowDetail -> startDetailActivity(news)
            }
        })
    }

    override fun accept(viewModel: ComicsViewModel) {
        when (viewModel) {
            ComicsViewModel.Starting -> return
            ComicsViewModel.GettingComics -> renderGettingComics()
            is SuccessGettingComics -> renderComicsAvailable(viewModel)
            is ComicsViewModel.Failure -> renderError(viewModel)
        }
    }

    private fun renderComicsAvailable(viewModel: SuccessGettingComics) {
        progress.visibility = View.GONE
        recycler.onItemClick = { comicsBook, image ->
            uiEvents.accept(ComicBookSelected(comicsBook.id))
        }
        recycler.onComicsAvailable(viewModel.results)
    }

    private fun renderGettingComics() {
        progress.visibility = View.VISIBLE
    }

    private fun renderError(viewModel: ComicsViewModel.Failure) {
        progress.visibility = View.GONE
        Snackbar.make(activity_main, viewModel.message, LENGTH_LONG).show()
    }


    fun startDetailActivity(comicsViewModel: MainFeature.News.ShowDetail) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.COMICS_EXTRA, comicsViewModel)
        startActivity(intent)
    }

}
