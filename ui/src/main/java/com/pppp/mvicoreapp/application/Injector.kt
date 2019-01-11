package com.pppp.mvicoreapp.application

import android.app.Activity
import android.content.Context
import com.pppp.mvicoreapp.detail.view.DetailActivity
import com.pppp.mvicoreapp.main.di.ActivityModule
import com.pppp.mvicoreapp.main.view.MainActivity
import com.pppp.mvicoreapp.main.view.customview.MarvelRecyclerView

// TODO throw away
object Injector {

    fun inject(mainActivity: MainActivity) {
        val component = getComponentFromActivity(mainActivity)
        //component?.withMainComponent(ActivityModule(mainActivity))?.inject(mainActivity)
    }

    fun inject(detailActivity: DetailActivity, comicId: Int) {
        val activityModule = ActivityModule(detailActivity, comicId)
        //getComponentFromActivity(detailActivity)?.withDetailComponent(activityModule)?.inject(detailActivity)
    }

    fun inject(recyclerView: MarvelRecyclerView) {
        val component = getAppComponent(recyclerView.context)
        component?.inject(recyclerView)
    }

    private fun getComponentFromActivity(activity: Activity) = getAppComponent(activity as Context)

    private fun getAppComponent(context: Context) = getApp(context)?.component

    private fun getApp(context: Context) = context.applicationContext as? App
}