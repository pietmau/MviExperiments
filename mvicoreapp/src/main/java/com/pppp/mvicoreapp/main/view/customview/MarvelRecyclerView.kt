package com.pppp.mvicoreapp.main.view.customview


import android.content.Context
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.ImageView
import com.pppp.mvicoreapp.main.di.Injector
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
    private var time = System.currentTimeMillis()

    private val marvelAdapter
        get() = adapter as MarvelAdapter

    var onItemClick: OnItemClick? by observable(null)
    { _: KProperty<*>, _: OnItemClick?, newValue: OnItemClick? ->
        marvelAdapter.onItemClick = { comicsBook, image ->
            onItemClicked(newValue, comicsBook, image)
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

    private fun onItemClicked(
        newValue: OnItemClick?,
        comicsBook: ComicsBookViewModel,
        image: ImageView
    ) {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - time) > DELAY_IN_MILLS) {
            newValue?.invoke(comicsBook, image)
        }
        time = currentTime
    }

    companion object {
        private const val MANY_ROWS = 5
        private const val FEW_ROWS = 3
        private const val DELAY_IN_MILLS = 500
    }
}

typealias OnItemClick = (ComicsBookViewModel, ImageView) -> Unit