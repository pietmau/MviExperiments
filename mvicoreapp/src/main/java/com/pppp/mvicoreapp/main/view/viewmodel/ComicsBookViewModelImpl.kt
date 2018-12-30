package com.pppp.mvicoreapp.main.view.viewmodel


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface ComicsBookViewModel {
    val id: String
    val title: String
    val imageUrl: String
    val description: String
    val pageCount: Int
    val price: String
    val authors: String
    val priceAsString: String
    val numberOfPagesAsString: String
}

@Parcelize
data class ComicsBookViewModelImpl(
    override val id: String = "",
    override val title: String = "",
    override val imageUrl: String = "",
    override val description: String = "",
    override val pageCount: Int = 0,
    override val price: String = "",
    override val authors: String = "",
    override val priceAsString: String = "",
    override val numberOfPagesAsString: String = ""
) : ComicsBookViewModel, Parcelable