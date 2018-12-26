package com.pppp.mvicoreapp.main.view.customview

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModelImpl
import kotlinx.android.synthetic.main.comics_item.view.*

class ComicsHolder(
    itemView: View,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: ComicsBookViewModelImpl, onItemClick: OnItemClick?) {
        imageLoader.loadImage(itemView.image, model.imageUrl, {
            itemView.setOnClickListener {
                onItemClick?.invoke(model, itemView.image)
            }
        })
    }

    fun unbind() {
        imageLoader.cancelTask(itemView.image)
        itemView.setOnClickListener(null)
    }
}
