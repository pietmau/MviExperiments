package com.pppp.mvicoreapp.main.view.viewmodel


import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// TODO remove
data class ComicsBookViewModel(
    val title: String,
    val imageUrl: String,
    val description: String,
    val pageCount: Int,
    val price: String,
    val authors: String,
    val priceAsString: String,
    val numberOfPagesAsString: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        (parcel.readValue(Int::class.java.classLoader) as Int?) ?: 0,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(imageUrl)
        dest.writeString(description)
        dest.writeValue(pageCount)
        dest.writeString(price)
        dest.writeString(authors)
        dest.writeString(priceAsString)
        dest.writeString(numberOfPagesAsString)
    }

    companion object CREATOR : Parcelable.Creator<ComicsBookViewModel> {
        override fun createFromParcel(parcel: Parcel): ComicsBookViewModel {
            return ComicsBookViewModel(parcel)
        }

        override fun newArray(size: Int): Array<ComicsBookViewModel?> {
            return arrayOfNulls(size)
        }
    }
}
