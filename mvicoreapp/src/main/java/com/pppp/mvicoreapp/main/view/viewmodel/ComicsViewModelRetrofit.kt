package com.marvel.marvel.viewmodel


import android.os.Parcel
import android.os.Parcelable

@Deprecated("We are not using data binding anymore, this is not longer needed")
class ComicsViewModelRetrofit : ComicsViewModel {
    override val title: String?
    override val imageUrl: String?
    override val description: String?
    override val pageCount: Int?
    override val price: String?
    override val authors: String?
    override val priceAsString: String?
    override val numberOfPagesAsString: String?

    constructor(builder: ComicsViewModelRetrofitBuilder) {
        title = builder.title
        imageUrl = builder.imageUrl
        description = builder.description
        pageCount = builder.pageCount
        price = builder.price
        authors = builder.authors
        priceAsString = builder.priceAsString
        numberOfPagesAsString = builder.numberOfPagesAsString
    }

    protected constructor(parcel: Parcel) {
        title = parcel.readString()
        imageUrl = parcel.readString()
        description = parcel.readString()
        pageCount = parcel.readValue(Int::class.java.classLoader) as Int?
        price = parcel.readString()
        authors = parcel.readString()
        priceAsString = parcel.readString()
        numberOfPagesAsString = parcel.readString()
    }

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

    companion object CREATOR : Parcelable.Creator<ComicsViewModelRetrofit> {
        override fun createFromParcel(parcel: Parcel): ComicsViewModelRetrofit {
            return ComicsViewModelRetrofit(parcel)
        }

        override fun newArray(size: Int): Array<ComicsViewModelRetrofit?> {
            return arrayOfNulls(size)
        }
    }
}
