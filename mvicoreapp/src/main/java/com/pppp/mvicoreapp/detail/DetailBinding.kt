package com.pppp.mvicoreapp.detail

import android.support.v7.app.AppCompatActivity
import com.badoo.mvicore.android.lifecycle.CreateDestroyBinderLifecycle
import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.using
import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.detail.view.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.DetailViewModel
import io.reactivex.functions.Consumer

interface DetailBinding {

    fun bind(
        viewModels: Consumer<DetailViewModel>,
        detailUiEvents: PublishRelay<DetailUiEvent>
    )
}

class DetailBindingImpl(
    activity: AppCompatActivity,
    private val feature: DetailFeature,
    private val viewModelTransformer: DetailViewModelTransformer,
    private val uiEventFransformer: UiEventTransformer
) : DetailBinding {
    private val binder: Binder

    init {
        val binderLifecycle = CreateDestroyBinderLifecycle(activity.lifecycle)
        binder = Binder(binderLifecycle)
    }

    override fun bind(
        viewModels: Consumer<DetailViewModel>,
        detailUiEvents: PublishRelay<DetailUiEvent>
    ) {
        binder.bind(feature to viewModels using viewModelTransformer)
        binder.bind(detailUiEvents to feature using uiEventFransformer)
    }
}