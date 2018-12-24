package com.pppp.mvicoreapp.detail

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.main.MainFeature
import com.pppp.mvicoreapp.main.di.Injector
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import kotlinx.android.synthetic.main.detail_activity.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    @Inject
    lateinit var loader: ImageLoader
    private var callback: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        Injector.inject(this)
    }

    override fun onResume() {
        super.onResume()
        loadImageAndColors()
    }

    override fun onPause() {
        super.onPause()
        loader.cancelTask(image)
    }

    private fun loadImageAndColors() {
        intent.getParcelableExtra<MainFeature.News.ShowDetail>(COMICS_EXTRA)?.let {
            setImageAndColors(it)
            setTexts(it)
        }
    }

    private fun setTexts(model: MainFeature.News.ShowDetail) {
        number_of_pages.text = model.numberOfPagesAsString
        price.text = model.price
        title_tv.text = model.title
        description.text = model.description
        authors.text = model.authors
    }

    private fun setImageAndColors(model: MainFeature.News.ShowDetail) {
        callback = {
            val loadedImage = (image.drawable as? BitmapDrawable)?.bitmap
            setColors(loadedImage)
        }
        loader.loadImage(image, model.imageUrl, callback)
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
        const val COMICS_EXTRA = "comix_extra"
    }
}
