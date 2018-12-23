package com.pppp.mvicoreapp.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.main.di.Injector
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel.ShowDetail
import kotlinx.android.synthetic.main.detail_activity.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    @Inject
    lateinit var loader: ImageLoader
    //private var transitionListener: SimpleTransitionListener? = null
    //private var isConfigChange: Boolean = false
    //private var callback: ImageLoader.Callback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        supportPostponeEnterTransition()
        Injector.inject(this)
    }

    override fun onResume() {
        super.onResume()
        loadImageAndColors()
    }

    override fun onPause() {
        super.onPause()
        loader.cancelTask(image)
//        callback = null
//        if (isLollipopOrAbove)
//            @SuppressLint("NewApi") {
//                window.removeListener(transitionListener)
//            }
    }

    private fun loadImageAndColors() {
        intent?.getParcelableExtra<ShowDetail>(COMICS_EXTRA)?.let {
            setImageAndColors(it)
            setTexts(it)
        }
    }

    private fun setTexts(model: ShowDetail) {
        number_of_pages.text = model.numberOfPagesAsString
        price.text = model.price
        title_tv.text = model.title
        description.text = model.description
        authors.text = model.authors
    }

    private fun setImageAndColors(model: ShowDetail) {
        /*callback = object : ImageLoader.SimpleCallback() {
            override fun onSuccess() {
                supportStartPostponedEnterTransition()
                val loadedImage = (image.drawable as? BitmapDrawable)?.bitmap
                waitForAnimationAndSetColors(loadedImage)
            }
        }*/
        loader.loadImage(image, model.imageUrl, {})
    }


//    private fun waitForAnimationAndSetColors(loadedImage: Bitmap?) {
//        if (isLollipopOrAbove && !isConfigChange)
//            @SuppressLint("NewApi") {
//                transitionListener = object : SimpleTransitionListener() {
//                    override fun onTransitionEnd(transition: Transition?) {
//                        setColors(loadedImage)
//                    }
//                }
//                window.addListener(transitionListener)
//            } else {
//            setColors(loadedImage)
//        }
//    }

//    private fun setColors(loadedImage: Bitmap?) {
//        Palette.from(loadedImage)
//            .generate { palette ->
//                val swatch = palette?.dominantSwatch ?: return@generate
//                swatch.rgb.let {
//                    background.setBackgroundColor(it)
//                }
//                swatch.bodyTextColor.let {
//                    number_of_pages.setTextColor(it)
//                    price.setTextColor(it)
//                    description.setTextColor(it)
//                    authors.setTextColor(it)
//                }
//                swatch.titleTextColor.let {
//                    title_tv.setTextColor(it)
//                }
//            }
//    }

    companion object {
        const val COMICS_EXTRA = "comix_extra"
    }
}
