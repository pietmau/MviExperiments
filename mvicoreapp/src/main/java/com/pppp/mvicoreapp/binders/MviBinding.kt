package com.pppp.mvicoreapp.binders

import com.pppp.mvicoreapp.main.MainViewModel
import io.reactivex.functions.Consumer

interface MviBinding {
    fun bind(consumer: Consumer<MainViewModel>)
}