package com.pppp.mvicoreapp.main

import android.support.v7.app.AppCompatActivity
import com.badoo.mvicore.android.lifecycle.CreateDestroyBinderLifecycle
import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.using
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import com.pppp.mvicoreapp.main.view.viewmodel.ViewModelTransformer
import io.reactivex.functions.Consumer

class MviBindingImpl(
    appCompatActivity: AppCompatActivity,
    private val feature: MainFeature,
    private val viewModelTransformer: ViewModelTransformer,
    private val uiEventTransformer: UiEventTransformer
) : MviBinding {
    private val binder: Binder

    init {
        val binderLifecycle = CreateDestroyBinderLifecycle(appCompatActivity.lifecycle)
        binder = Binder(binderLifecycle)
    }

    override fun bind(
        consumer: Consumer<ComicsViewModel>,
        uiEvents: PublishRelay<UiEventTransformer.UiEvent>
    ) {
        binder.bind(feature to consumer using viewModelTransformer)
        binder.bind(uiEvents to feature using uiEventTransformer)
    }
}