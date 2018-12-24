package com.marvel.marvel.main.model.pojos

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NetworkPrice(
    @SerializedName("type")
    @Expose(serialize = false, deserialize = false)
    var type: String? = null,
    @SerializedName("price")
    @Expose
    override var price: String? = null
) : com.pppp.foo.Price, Parcelable
