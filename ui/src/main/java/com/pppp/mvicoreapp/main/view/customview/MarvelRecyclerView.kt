package com.pppp.mvicoreapp.main.view.customview

import android.content.Context
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.util.AttributeSet
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.application.Injector
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel
import javax.inject.Inject
import kotlin.properties.Delegates.observable
import kotlin.reflect.KProperty

class MarvelRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {
    @Inject
    lateinit var loader: ImageLoader
    @Inject
    lateinit var clickBlocker: ClickBlocker

    private val marvelAdapter
        get() = adapter as MarvelAdapter

    var onItemClick: OnItemClick? by observable(null) { _: KProperty<*>, _: OnItemClick?, _: OnItemClick? ->
        marvelAdapter.onItemClick = { comicsBook, position ->
            onItemClicked(comicsBook, position)
        }
    }

    private val span: Int
        get() = if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) MANY_ROWS else FEW_ROWS

    init {
        Injector.inject(this)
        layoutManager = GridLayoutManager(this.context, span)
        adapter = MarvelAdapter(loader)
    }

    fun onComicsAvailable(results: List<ComicsBookViewModel>) {
        marvelAdapter.setItems(results)
    }

    private fun onItemClicked(comicsBook: ComicsBookViewModel, position: Int) {
        clickBlocker.executeIfAppropriate(this, position) {
            onItemClick?.invoke(comicsBook, position)
        }
    }

    fun getImageViewAtPostiion(position: Int) =
        layoutManager?.findViewByPosition(position)?.findViewById<ImageView>(R.id.image)

    companion object {
        private const val MANY_ROWS = 5
        private const val FEW_ROWS = 3
    }
}

typealias OnItemClick = (ComicsBookViewModel, Int) -> Unit
