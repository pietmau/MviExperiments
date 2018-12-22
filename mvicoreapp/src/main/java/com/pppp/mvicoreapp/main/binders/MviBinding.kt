package com.pppp.mvicoreapp.main.binders

import com.pppp.mvicoreapp.main.view.MainViewModel
import io.reactivex.functions.Consumer

interface MviBinding {
    fun bind(consumer: Consumer<MainViewModel>)
}