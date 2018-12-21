package com.pppp.mvicoreapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.badoo.mvicore.android.lifecycle.CreateDestroyBinderLifecycle
import com.badoo.mvicore.binder.Binder
import io.reactivex.functions.Consumer

class MainActivity : AppCompatActivity(), Consumer<MainFeature.MainState> {
    lateinit var binder: Binder
    private val feature = MainFeature()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binderLifecycle = CreateDestroyBinderLifecycle(this.lifecycle)
        binder = Binder(binderLifecycle)
        binder.bind(feature to this)
    }

    override fun accept(t: MainFeature.MainState?) {

    }


}
