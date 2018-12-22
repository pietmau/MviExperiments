package com.marvel.marvel.application

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(view: ImageView, url: String?, callback: Callback?)
    fun cancelTask(image: ImageView)

    interface Callback {
        fun onSuccess()

        fun onError(e: Exception?)
    }

    open class SimpleCallback : Callback {
        override fun onSuccess() {

        }

        override fun onError(e: Exception?) {

        }
    }
}