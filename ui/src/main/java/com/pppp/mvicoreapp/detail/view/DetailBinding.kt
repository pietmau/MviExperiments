package com.pppp.mvicoreapp.detail.view

import androidx.appcompat.app.AppCompatActivity
import com.badoo.mvicore.android.lifecycle.CreateDestroyBinderLifecycle
import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.using
import com.jakewharton.rxrelay2.Relay
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEvent
import com.pppp.mvicoreapp.detail.view.uievent.DetailUiEventTransformer
import com.pppp.mvicoreapp.detail.view.viewmodel.DetailViewModel
import com.pppp.mvicoreapp.detail.view.viewmodel.DetailViewModelTransformer
import com.pppp.usecases.detail.DetailFeature
import io.reactivex.functions.Consumer

interface DetailBinding {

    fun bind(
        viewModels: Consumer<DetailViewModel>,
        detailUiEvents: Relay<DetailUiEvent>
    )
}

class DetailBindingImpl(
    activity: AppCompatActivity,
    private val feature: DetailFeature,
    private val viewModelTransformer: DetailViewModelTransformer,
    private val uiEventFransformer: DetailUiEventTransformer
) : DetailBinding {
    private val binder: Binder

    init {
        val binderLifecycle = CreateDestroyBinderLifecycle(activity.lifecycle)
        binder = Binder(binderLifecycle)
    }

    override fun bind(
        viewModels: Consumer<DetailViewModel>,
        detailUiEvents: Relay<DetailUiEvent>
    ) {
        binder.bind(feature to viewModels using viewModelTransformer)
        binder.bind(detailUiEvents to feature using uiEventFransformer)
    }
}
