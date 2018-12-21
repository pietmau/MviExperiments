package com.pppp.mvicoreapp.binders

import androidx.appcompat.app.AppCompatActivity
import com.badoo.mvicore.android.lifecycle.CreateDestroyBinderLifecycle
import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.using
import com.pppp.mvicoreapp.main.MainFeature
import com.pppp.mvicoreapp.main.MainViewModel
import com.pppp.mvicoreapp.main.ViewModelTrasformer
import io.reactivex.functions.Consumer

class MviBindingImpl(
    appCompatActivity: AppCompatActivity,
    private val feature: MainFeature,
    private val trasformer: ViewModelTrasformer
) : MviBinding {
    private val binder: Binder

    init {
        val binderLifecycle = CreateDestroyBinderLifecycle(appCompatActivity.lifecycle)
        binder = Binder(binderLifecycle)
    }

    override fun bind(consumer: Consumer<MainViewModel>) {
        binder.bind(feature to consumer using trasformer)
    }
}