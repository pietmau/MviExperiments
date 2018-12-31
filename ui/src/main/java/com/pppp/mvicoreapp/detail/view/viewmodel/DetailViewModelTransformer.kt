package com.pppp.mvicoreapp.detail.view.viewmodel

import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.usecases.detail.DetailFeature

class DetailViewModelTransformerImpl(private val mapper: ComicsBookMapper) :
    DetailViewModelTransformer {

    override fun invoke(state: DetailFeature.State): DetailViewModel {
        return when (state) {
            DetailFeature.State.Starting -> DetailViewModel.Starting
            DetailFeature.State.GettingData -> DetailViewModel.GettingData
            is DetailFeature.State.GotData -> DetailViewModel.GotNewData(mapper.map(state.comicsBook))
            is DetailFeature.State.Error -> DetailViewModel.Error(state.errorMessage)
        }
    }
}

typealias DetailViewModelTransformer = (DetailFeature.State) -> DetailViewModel