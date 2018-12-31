package com.pppp.mvicoreapp.detail.view

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.application.Injector
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.viewmodel.DetailViewModel
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import com.pppp.mvicoreapp.main.view.showError
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.detail_activity.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    @Inject
    lateinit var loader: ImageLoader
    @Inject
    lateinit var binding: DetailBinding
    @Inject
    lateinit var uiEvents: Relay<DetailUiEvent>

    private val viewModels = Consumer<DetailViewModel> { viewModel ->
        render(viewModel)
    }
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        val comicId = intent.getIntExtra(COMICS_ID_EXTRA, -1)
        Injector.inject(this, comicId)
        binding.bind(viewModels, uiEvents)
        scroller.addOnGlobalLayoutListener {
            scroller.translationY = window.decorView.bottom.toFloat() - scroller.top
            animateIn(scroller)
            handler.postDelayed(
                { container.setBackgroundColor((scroller.background as ColorDrawable).color) }, 500
            )
        }
    }

    private fun render(viewModel: DetailViewModel) =
        when (viewModel) {
            is DetailViewModel.GotNewData -> populateTextFields(viewModel)
            is DetailViewModel.Error -> showError(viewModel.errorMessage)
            else -> { /* NoOp */
            }
        }

    private fun populateTextFields(viewModel: DetailViewModel.GotNewData) {
        number_of_pages.text = viewModel.numberOfPagesAsString
        price.text = viewModel.price
        title_tv.text = viewModel.title
        description.text = viewModel.description
        authors.text = viewModel.authors
        setImageAndColors(viewModel.imageUrl)
    }

    private fun setImageAndColors(url: String) {
        loader.loadImage(image, url, {
            val loadedImage = (image.drawable as? BitmapDrawable)?.bitmap
            setColors(loadedImage)
        })
    }

    private fun setColors(loadedImage: Bitmap?) {
        loadedImage ?: return
        Palette.from(loadedImage).dominantSwatch {
            it.apply {
                scroller.setBackgroundColor(rgb)
                number_of_pages.setTextColor(bodyTextColor)
                price.setTextColor(bodyTextColor)
                description.setTextColor(bodyTextColor)
                authors.setTextColor(bodyTextColor)
                title_tv.setTextColor(titleTextColor)
            }
        }
    }

    override fun onBackPressed() {
        animateOut(window.decorView, scroller)
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    companion object {
        const val COMICS_ID_EXTRA = "comix_extra"
    }
}
