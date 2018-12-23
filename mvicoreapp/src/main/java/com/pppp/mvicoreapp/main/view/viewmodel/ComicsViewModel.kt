package com.pppp.mvicoreapp.main.view.viewmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class ComicsViewModel {
    object Starting : ComicsViewModel()
    object GettingComics : ComicsViewModel()
    data class SuccessGettingComics(val results: List<ComicsBookViewModel>) : ComicsViewModel()
    data class Failure(val message: String?) : ComicsViewModel()

    @Parcelize
    data class ShowDetail(val comicsBook: ComicsBookViewModel) : ComicsViewModel(), Parcelable {
        val numberOfPagesAsString = comicsBook.numberOfPagesAsString
        val price = comicsBook.price
        val title = comicsBook.title
        val description = comicsBook.description
        val authors = comicsBook.authors
        val imageUrl = comicsBook.imageUrl
    }
}