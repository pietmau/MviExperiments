package com.pppp.mvicoreapp.detail.view.viewmodel

import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookViewModel

sealed class DetailViewModel {
    object Starting : DetailViewModel()
    object GettingData : DetailViewModel()
    data class GotNewData(val result: ComicsBookViewModel) : DetailViewModel() {
        val numberOfPagesAsString: String?
            get() = result.numberOfPagesAsString
        val price: String?
            get() = result.price
        val title: String?
            get() = result.title
        val description: String?
            get() = result.description
        val authors: String?
            get() = result.authors
        val imageUrl: String
            get() = result.imageUrl
    }
    data class Error(val errorMessage: String?) : DetailViewModel()
}