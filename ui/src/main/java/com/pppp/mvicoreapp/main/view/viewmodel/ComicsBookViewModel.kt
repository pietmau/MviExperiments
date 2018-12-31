package com.pppp.mvicoreapp.main.view.viewmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicsBookViewModel(
    val id: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val pageCount: Int = 0,
    val price: String = "",
    val authors: String = "",
    val priceAsString: String = "",
    val numberOfPagesAsString: String = ""
) : Parcelable