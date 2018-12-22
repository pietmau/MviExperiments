package com.marvel.marvel.viewmodel

import android.os.Parcelable

@Deprecated("We are not using data binding anymore, this is not longer needed")
interface ComicsViewModel : Parcelable {
    val authors: String?

    val pageCount: Int?

    val price: String?

    val description: String?

    val numberOfPagesAsString: String?

    val priceAsString: String?

    val title: String?

    val imageUrl: String?
}
