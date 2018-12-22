package com.marvel.marvel.application

import android.widget.ImageView
import com.pppp.mvicoreapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

object PicassoImageLoader : ImageLoader {

    override fun loadImage(view: ImageView, url: String?, callback: ImageLoader.Callback?) {
        url ?: return
        Picasso.get()
            .load(url)
            .error(R.drawable.marvel_small)
            .into(view, object : Callback {
                override fun onSuccess() {
                    callback?.onSuccess()
                }

                override fun onError(exception: Exception?) {
                    callback?.onError(exception)
                }
            })
    }

    override fun cancelTask(image: ImageView) {
        Picasso.get().cancelRequest(image)
    }

}
