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
    lateinit var timer: Timer

    private val marvelAdapter
        get() = adapter as MarvelAdapter

    var onItemClick: OnItemClick? by observable(null)
    { _: KProperty<*>, _: OnItemClick?, newValue: OnItemClick? ->
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

    private fun onItemClicked(
        comicsBook: ComicsBookViewModel,
        image: Int
    ) {
        timer.waitOrExecute(comicsBook, image, onItemClick)

    }

    fun getImageViewAtPostiion(position: Int) =
        layoutManager?.findViewByPosition(position)?.findViewById<ImageView>(R.id.image)

    companion object {
        private const val MANY_ROWS = 5
        private const val FEW_ROWS = 3

    }
}

class TimerImpl : Timer {
    private var time = System.currentTimeMillis()

    override fun waitOrExecute(comicsBook: ComicsBookViewModel, image: Int, onItemClick: OnItemClick?) {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - time) > DELAY_IN_MILLS) {
            onItemClick?.invoke(comicsBook, image)
        }
        time = currentTime
    }

    companion object {
        private const val DELAY_IN_MILLS = 500
    }
}

interface Timer {

    fun waitOrExecute(comicsBook: ComicsBookViewModel, image: Int, onItemClick: OnItemClick?)
}

typealias OnItemClick = (ComicsBookViewModel, Int) -> Unit