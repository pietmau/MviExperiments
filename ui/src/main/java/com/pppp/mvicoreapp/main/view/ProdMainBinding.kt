package com.pppp.mvicoreapp.main.view

import androidx.appcompat.app.AppCompatActivity
import com.badoo.mvicore.android.lifecycle.CreateDestroyBinderLifecycle
import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.using
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.main.view.uievent.MainUiEvent
import com.pppp.mvicoreapp.main.view.uievent.MainUiEventTransformer
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.mvicoreapp.main.view.viewmodel.ViewModelTransformer
import com.pppp.usecases.main.MainFeature
import io.reactivex.functions.Consumer

open class ProdMainBinding(
    appCompatActivity: AppCompatActivity,
    private val feature: MainFeature,
    private val viewModelTransformer: ViewModelTransformer,
    private val uiEventTransformer: MainUiEventTransformer
) : MainBinding {
    private val binder: Binder

    init {
        val binderLifecycle = CreateDestroyBinderLifecycle(appCompatActivity.lifecycle)
        binder = Binder(binderLifecycle)
    }

    override fun bind(
        viewModels: Consumer<ComicsViewModel>,
        uiEvents: PublishRelay<MainUiEvent>,
        news: Consumer<MainFeature.News>
    ) {
        binder.bind(feature to viewModels using viewModelTransformer)
        binder.bind(uiEvents to feature using uiEventTransformer)
        binder.bind(feature.news to news)
    }
}

interface MainBinding {

    fun bind(
        viewModels: Consumer<ComicsViewModel>,
        uiEvents: PublishRelay<MainUiEvent>,
        news: Consumer<MainFeature.News>
    )
}
