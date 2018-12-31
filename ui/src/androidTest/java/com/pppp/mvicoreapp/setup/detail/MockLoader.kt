package com.pppp.mvicoreapp.setup.detail

import android.widget.ImageView
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.main.view.customview.Failure
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import com.pppp.mvicoreapp.main.view.customview.Success

object MockLoader : ImageLoader {
    override fun loadImage(view: ImageView, url: String?, success: Success?, failure: Failure?) {
        view.setImageResource(R.drawable.marvel)
        success?.invoke()
    }
}