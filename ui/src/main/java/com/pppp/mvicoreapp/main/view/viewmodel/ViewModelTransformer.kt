package com.pppp.mvicoreapp.main.view.viewmodel

import com.pppp.features.main.MainFeature

class ViewModelTransformer(private val mapper: ComicsBookMapper) :
        (MainFeature.State) -> ComicsViewModel {

    override fun invoke(state: MainFeature.State): ComicsViewModel {
        fun transformComics(state: MainFeature.State.SuccessGettingComics) =
            (state.comicsBooks ?: emptyList()).map { mapper.map(it) }

        return when (state) {
            MainFeature.State.Starting -> ComicsViewModel.Starting
            MainFeature.State.GettingComics -> ComicsViewModel.GettingComics
            is MainFeature.State.SuccessGettingComics -> ComicsViewModel.SuccessGettingComics(
                transformComics(state)
            )
            is MainFeature.State.FailureGettingComics -> ComicsViewModel.Failure(state.error.localizedMessage)
        }
    }
}