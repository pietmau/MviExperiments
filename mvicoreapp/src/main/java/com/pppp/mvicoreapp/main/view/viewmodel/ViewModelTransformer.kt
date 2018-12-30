package com.pppp.mvicoreapp.main.view.viewmodel

import com.pppp.mvicoreapp.main.MainFeature
import com.pppp.mvicoreapp.main.MainFeature.State
import com.pppp.mvicoreapp.main.MainFeature.State.*

class ViewModelTransformer(private val mapper: ComicsBookMapper) :
        (MainFeature.State) -> ComicsViewModel {

    override fun invoke(state: State): ComicsViewModel {
        fun transformComics(state: SuccessGettingComics) =
            (state.comicsBooks ?: emptyList()).map { mapper.map(it) }

        return when (state) {
            Starting -> ComicsViewModel.Starting
            GettingComics -> ComicsViewModel.GettingComics
            is SuccessGettingComics -> ComicsViewModel.SuccessGettingComics(transformComics(state))
            is FailureGettingComics -> ComicsViewModel.Failure(state.error.localizedMessage)
        }
    }
}