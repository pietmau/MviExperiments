package com.pppp.mvicoreapp.main.view.customview

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel


internal class MarvelAdapter(private val loader: ImageLoader) :
    RecyclerView.Adapter<ComicsHolder>() {
    private val comicBooks: MutableList<ComicsBookViewModel> = mutableListOf()
    var onItemClick: OnItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ComicsHolder(inflater.inflate(R.layout.comics_item, null), loader)
    }

    override fun onBindViewHolder(holder: ComicsHolder, position: Int) {
        holder.bind(comicBooks[position], onItemClick)
    }

    override fun getItemCount() = comicBooks.size

    override fun onViewRecycled(holder: ComicsHolder) {
        holder.unbind()
    }

    fun setItems(newResults: List<ComicsBookViewModel>) {
        DiffUtil.calculateDiff(MarvelDiffCallback(comicBooks, newResults)).dispatchUpdatesTo(this)
        comicBooks.clear()
        comicBooks.addAll(newResults)
    }

    private class MarvelDiffCallback(
        private val old: List<ComicsBookViewModel>,
        private val new: List<ComicsBookViewModel>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition] == new[newItemPosition]

        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition] == new[newItemPosition]
    }
}
