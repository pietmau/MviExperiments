package com.pppp.mvicoreapp.di

import com.pppp.mvicoreapp.App
import com.pppp.mvicoreapp.main.MainActivity

object Injector {

    fun inject(mainActivity: MainActivity) {
        (mainActivity.application as? App)?.component?.with(
            MviCoreModule(
                mainActivity
            )
        )
            ?.inject(mainActivity)
    }
}