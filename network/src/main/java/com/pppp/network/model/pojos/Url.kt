package com.marvel.marvel.main.model.pojos

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Url(
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null
) : Parcelable
