package com.marvel.marvel.main.model.pojos

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NetworkCreators(
    @SerializedName("available")
    @Expose(serialize = false, deserialize = false)
    var available: Long = 0,

    @SerializedName("collectionURI")
    @Expose(serialize = false, deserialize = false)
    var collectionURI: String? = null,

    @SerializedName("items")
    @Expose
    var items: List<NetworkItem>? = null,

    @SerializedName("returned")
    @Expose(serialize = false, deserialize = false)
    var returned: Long = 0
) : Parcelable
