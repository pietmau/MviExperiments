package com.marvel.marvel.customview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.marvel.marvel.R
import com.marvel.marvel.application.ImageLoader
import com.marvel.marvel.viewmodel.ComicsViewModel


internal class MarvelAdapter(private val loader: ImageLoader) :
    RecyclerView.Adapter<ComicsHolder>() {
    private val results: MutableList<ComicsViewModel> = mutableListOf()
    var callback: MarvelRecyclerView.Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ComicsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comics_item, null),
            loader
        )

    override fun onBindViewHolder(holder: ComicsHolder, position: Int) {
        holder.bind(results[position], callback)
    }

    override fun getItemCount() = results.size

    override fun onViewRecycled(holder: ComicsHolder) {
        holder.unbind()
    }

    fun setItems(results: List<ComicsViewModel>) {
        if (this.results.isEmpty()) {
            this.results.clear()
            this.results.addAll(results)
            notifyDataSetChanged()
        }
    }
}
