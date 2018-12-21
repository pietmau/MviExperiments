package com.pppp.mvicoreapp.main

import com.pppp.mvicoreapp.main.MainFeature.MainState.*

object ViewModelTrasformer : (MainFeature.MainState) -> MainViewModel {

    override fun invoke(state: MainFeature.MainState): MainViewModel =
        when (state) {
            Starting -> MainViewModel.Starting
            GettingComics -> MainViewModel.GetingComics
            is SuccessGettingComics -> MainViewModel.Success(state.results ?: emptyList())
            is FailureGettingComics -> MainViewModel.Failure(state.error.localizedMessage)
        }
}