package com.marvel.marvel.main.model.pojos

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.foo.Item
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NetworkItem(
    @SerializedName("resourceURI")
    @Expose(serialize = false, deserialize = false)
    var resourceURI: String? = null,

    @SerializedName("name")
    @Expose
    override var name: String? = null,

    @SerializedName("role")
    @Expose(serialize = false, deserialize = false)
    var role: String? = null
) : Item, Parcelable
