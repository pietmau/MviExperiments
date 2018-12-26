package com.pppp.mvicoreapp.application

import android.app.Activity
import android.content.Context
import com.pppp.mvicoreapp.detail.DetailActivity
import com.pppp.mvicoreapp.main.di.MainModule
import com.pppp.mvicoreapp.main.MainActivity
import com.pppp.mvicoreapp.main.view.customview.MarvelRecyclerView

object Injector {

    fun inject(mainActivity: MainActivity) {
        val component =
            getComponentFromActivity(mainActivity)
        component?.with(MainModule(mainActivity))?.inject(mainActivity)
    }

    fun inject(recyclerView: MarvelRecyclerView) {
        val component =
            getAppComponent(recyclerView.context)
        component?.inject(recyclerView)
    }


    fun inject(detailActivity: DetailActivity) {
        val component =
            getComponentFromActivity(detailActivity)
        component?.inject(detailActivity)
    }

    private fun getComponentFromActivity(activity: Activity) =
        getAppComponent(activity as Context)

    private fun getAppComponent(context: Context) = getApp(
        context
    )?.component

    private fun getApp(context: Context) = context.applicationContext as? App

}