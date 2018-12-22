package com.marvel.marvel.customview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.marvel.marvel.application.ImageLoader
import com.marvel.marvel.viewmodel.ComicsViewModel
import kotlinx.android.synthetic.main.comics_item.view.*

class ComicsHolder(
    itemView: View,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val CLICK_DELAY = 500
    }

    private var model: ComicsViewModel? = null
    private var callback: MarvelRecyclerView.Callback? = null
    private var time: Long? = null

    fun bind(model: ComicsViewModel, callback: MarvelRecyclerView.Callback?) {
        this.model = model
        this.callback = callback
        imageLoader.loadImage(itemView.image, model.imageUrl,
            object : ImageLoader.SimpleCallback() {
                override fun onSuccess() {
                    itemView.setOnClickListener {
                        onItemClicked()
                    }
                }
            })
    }

    private fun onItemClicked() {
        val lastClickTime = time ?: 0
        if (System.currentTimeMillis() - lastClickTime > CLICK_DELAY) {
            callback?.onItemClicked(model, itemView.image)
            time = System.currentTimeMillis()
        }
    }

    fun unbind() {
        imageLoader.cancelTask(itemView.image)
        itemView.setOnClickListener(null)
    }

}
