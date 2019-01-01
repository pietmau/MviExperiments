package com.pppp.mvicoreapp.main.view.customview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel
import kotlinx.android.synthetic.main.comics_item.view.*

class ComicsHolder(itemView: View, private val imageLoader: ImageLoader) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(model: ComicsBookViewModel, onItemClick: OnItemClick?) {
        imageLoader.loadImage(itemView.image, model.imageUrl, {
            itemView.setOnClickListener {
                onItemClick?.invoke(model, this.adapterPosition)
            }
        })
        itemView.item_title.text = model.title
    }

    fun unbind() {
        imageLoader.cancelTask(itemView.image)
        itemView.setOnClickListener(null)
    }
}
