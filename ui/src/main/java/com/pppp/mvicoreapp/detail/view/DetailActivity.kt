package com.pppp.mvicoreapp.detail.view

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.application.Injector
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.viewmodel.DetailViewModel
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.detail_activity.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    @Inject
    lateinit var loader: ImageLoader
    @Inject
    lateinit var binding: DetailBinding
    @Inject
    lateinit var detailUiEvents: PublishRelay<DetailUiEvent>
    private var callback: (() -> Unit)? = null
    val viewModels = Consumer<DetailViewModel> { viewModel ->
        render(viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        supportPostponeEnterTransition()
        Injector.inject(this, intent.getIntExtra(COMICS_ID_EXTRA, -1))
        binding.bind(viewModels, detailUiEvents)
    }

    private fun render(viewModel: DetailViewModel) {
        when (viewModel) {
            is DetailViewModel.GotNewData -> {
                number_of_pages.text = viewModel.numberOfPagesAsString
                price.text = viewModel.price
                title_tv.text = viewModel.title
                description.text = viewModel.description
                authors.text = viewModel.authors
                setImageAndColors(viewModel.imageUrl)
            }
        }
    }

    private fun setImageAndColors(url: String) {
        callback = {
            supportStartPostponedEnterTransition()
            val loadedImage = (image.drawable as? BitmapDrawable)?.bitmap
            setColors(loadedImage)
        }
        loader.loadImage(image, url, callback)
    }

    private fun setColors(loadedImage: Bitmap?) {
        loadedImage ?: return
        Palette.from(loadedImage)
            .generate {
                it?.dominantSwatch?.apply {
                    background.setBackgroundColor(rgb)
                    number_of_pages.setTextColor(bodyTextColor)
                    price.setTextColor(bodyTextColor)
                    description.setTextColor(bodyTextColor)
                    authors.setTextColor(bodyTextColor)
                    title_tv.setTextColor(titleTextColor)
                }
            }
    }

    companion object {
        const val COMICS_ID_EXTRA = "comix_extra"
    }
}
