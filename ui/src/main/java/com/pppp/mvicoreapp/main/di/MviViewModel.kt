package com.pppp.mvicoreapp.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pppp.usecases.main.MainActor
import com.pppp.usecases.main.MainFeature

class MviViewModel(mainActor: MainActor) : ViewModel() {
    val mainFeature: MainFeature by lazy {
        MainFeature(
            mainActor
        )
    }

    class Factory(private val mainActor: MainActor) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MviViewModel(mainActor) as T
        }
    }
}