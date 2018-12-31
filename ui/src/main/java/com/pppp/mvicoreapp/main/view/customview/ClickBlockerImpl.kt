package com.pppp.mvicoreapp.main.view.customview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ClickBlockerImpl : ClickBlocker {
    private var time = System.currentTimeMillis()

    override fun executeIfAppropriate(
        recycler: RecyclerView,
        position: Int,
        onItemClick: () -> Unit
    ) {
        if (isNotFullyVisible(recycler, position)) {
            return
        }
        val currentTime = System.currentTimeMillis()
        if ((currentTime - time) > DELAY_IN_MILLS) {
            onItemClick()
        }
        time = currentTime
    }

    private fun isNotFullyVisible(recyclerView: RecyclerView, position: Int): Boolean {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val first = layoutManager.findFirstCompletelyVisibleItemPosition()
        val last = layoutManager.findLastCompletelyVisibleItemPosition()
        return position !in first..last
    }

    companion object {
        private const val DELAY_IN_MILLS = 500
    }
}

interface ClickBlocker {

    fun executeIfAppropriate(recycler: RecyclerView, position: Int, onItemClick: () -> Unit)
}