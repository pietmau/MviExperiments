package com.pppp.mvicoreapp.main.view.customview

import android.widget.ImageView
import com.pppp.mvicoreapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

object PicassoImageLoader : ImageLoader {

    override fun loadImage(view: ImageView, url: String?, success: Success?, failure: Failure?) {
        url ?: return
        Picasso.get()
            .load(url)
            .error(R.drawable.marvel_small)
            .into(view, object : Callback {
                override fun onSuccess() {
                    success?.invoke()
                }

                override fun onError(exception: Exception?) {
                    failure?.invoke(exception)
                }
            })
    }

    override fun cancelTask(image: ImageView) {
        Picasso.get().cancelRequest(image)
    }

}
