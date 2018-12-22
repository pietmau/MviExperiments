package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.App
import com.pppp.mvicoreapp.main.view.MainActivity
import com.pppp.mvicoreapp.main.view.customview.MarvelRecyclerView

object Injector {

    fun inject(mainActivity: MainActivity) {
        val app = mainActivity.application as? App
        app?.component?.with(MviCoreModule(mainActivity))?.inject(mainActivity)
    }

    fun inject(recyclerView: MarvelRecyclerView) {
        (recyclerView.context.applicationContext as? App)?.component?.inject(recyclerView)
    }
}