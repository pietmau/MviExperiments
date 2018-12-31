package com.pppp.mvicoreapp.main.view.customview

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(view: ImageView, url: String?, success: Success? = {}, failure: Failure? = {})
    fun cancelTask(image: ImageView) {}
}
typealias Success = () -> Unit

typealias Failure = (Exception?) -> Unit