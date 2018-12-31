package com.pppp.mvicoreapp.main.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.application.Injector
import com.pppp.mvicoreapp.detail.view.DetailActivity
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.usecases.main.MainFeature
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Consumer<ComicsViewModel> {
    @Inject
    lateinit var binding: MainBinding
    @Inject
    lateinit var uiEvents: Relay<MainUiEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Injector.inject(this)
        binding.bind(this, uiEvents, Consumer { news ->
            when (news) {
                is MainFeature.News.ShowDetail -> startDetailActivity(news)
            }
        })
    }

    override fun accept(viewModel: ComicsViewModel) {
        when (viewModel) {
            ComicsViewModel.Starting -> return
            ComicsViewModel.GettingComics -> renderGettingComics()
            is ComicsViewModel.SuccessGettingComics -> renderComicsAvailable(viewModel)
            is ComicsViewModel.Failure -> renderError(viewModel)
        }
    }

    private fun renderComicsAvailable(viewModel: ComicsViewModel.SuccessGettingComics) {
        progress.visibility = View.GONE
        recycler.onComicsAvailable(viewModel.results)
        recycler.onItemClick = { comicsBook, position ->
            uiEvents.accept(MainUiEvent.ComicBookSelected(comicsBook.id, position))
        }
    }

    private fun renderGettingComics() {
        progress.visibility = View.VISIBLE
    }

    private fun renderError(viewModel: ComicsViewModel.Failure) {
        progress.visibility = View.GONE
        showError(viewModel.message)
    }

    private fun startDetailActivity(comicsViewModel: MainFeature.News.ShowDetail) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.COMICS_ID_EXTRA, comicsViewModel.id)
        val imageView = recycler.getImageViewAtPostiion(comicsViewModel.position)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this, imageView, getString(R.string.transition)
        )
        startActivity(intent, options.toBundle())
    }
}
