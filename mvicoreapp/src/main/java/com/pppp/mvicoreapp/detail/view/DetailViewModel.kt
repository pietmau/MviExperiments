package com.pppp.mvicoreapp.detail.view

import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModelImpl

sealed class DetailViewModel {
    object Starting : DetailViewModel()
    object GettingData : DetailViewModel()
    data class GotNewData(val result: ComicsBookViewModelImpl) : DetailViewModel(),
        ComicsBookViewModel by result {}
}