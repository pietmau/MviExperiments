package com.pppp.mvicoreapp.main.view.viewmodel

sealed class ComicsViewModel {
    object Starting : ComicsViewModel()
    object GettingComics : ComicsViewModel()
    data class SuccessGettingComics(val results: List<ComicsBookViewModelImpl>) : ComicsViewModel()
    data class Failure(val message: String?) : ComicsViewModel()
}