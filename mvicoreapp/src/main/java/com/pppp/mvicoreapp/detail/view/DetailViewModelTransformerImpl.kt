package com.pppp.mvicoreapp.detail.view

import com.pppp.mvicoreapp.detail.DetailFeature
import com.pppp.mvicoreapp.detail.DetailViewModelTransformer
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper

class DetailViewModelTransformerImpl(private val mapper: ComicsBookMapper) :
    DetailViewModelTransformer {

    override fun invoke(state: DetailFeature.State): DetailViewModel {
        return when (state) {
            DetailFeature.State.Starting -> DetailViewModel.Starting
            DetailFeature.State.GettingData -> DetailViewModel.GettingData
            is DetailFeature.State.GotData -> DetailViewModel.GotNewData(
                mapper.map(state.comicsBook)
            )
        }
    }
}