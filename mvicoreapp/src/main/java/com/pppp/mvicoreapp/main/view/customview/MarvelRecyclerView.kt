package com.marvel.marvel.customview


import android.content.Context
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.ImageView
import com.marvel.marvel.application.ImageLoader
import com.marvel.marvel.application.MarvelApplication
import com.marvel.marvel.viewmodel.ComicsViewModel
import javax.inject.Inject

class MarvelRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    @Inject
    lateinit var loader: ImageLoader

    companion object {
        private const val MANY_ROWS = 5
        private const val FEW_ROWS = 3
    }

    private val marvelAdapter
        get() = adapter as? MarvelAdapter

    private val span: Int
        get() = if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) MANY_ROWS else FEW_ROWS

    init {
        (this.context.applicationContext as? MarvelApplication)?.appComponent?.inject(this)
        layoutManager = GridLayoutManager(this.context, span)
        adapter = MarvelAdapter(loader)
    }

    fun onComicsAvailable(results: List<ComicsViewModel>) {
        marvelAdapter?.setItems(results)
    }

    fun setCallback(callback: Callback) {
        marvelAdapter?.callback = callback
    }

    interface Callback {
        fun onItemClicked(comicsViewModel: ComicsViewModel?, imageView: ImageView)
    }
}
