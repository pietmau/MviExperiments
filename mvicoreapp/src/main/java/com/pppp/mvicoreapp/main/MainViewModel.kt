package com.pppp.mvicoreapp.main

import com.marvel.marvel.main.model.pojos.Result

sealed class MainViewModel {
    object Starting : MainViewModel()
    object GetingComics : MainViewModel()
    data class Success(val results: List<Result>) : MainViewModel()
    data class Failure(val message: String?) : MainViewModel()
}