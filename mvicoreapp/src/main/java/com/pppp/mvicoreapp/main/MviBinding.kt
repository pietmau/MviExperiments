package com.pppp.mvicoreapp.main

import com.jakewharton.rxrelay2.PublishRelay
import com.pppp.mvicoreapp.main.view.uieventssource.UiEventTransformer
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsViewModel
import io.reactivex.functions.Consumer

interface MviBinding {

    fun bind(
        consumer: Consumer<ComicsViewModel>,
        uiEvents: PublishRelay<UiEventTransformer.UiEvent>,
        consum: Consumer<MainFeature.News>
    )
}