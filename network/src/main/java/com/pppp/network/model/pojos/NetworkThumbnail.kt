package com.marvel.marvel.main.model.pojos

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NetworkThumbnail(
    @SerializedName("path")
    @Expose
    override var path: String? = null,
    @SerializedName("extension")
    @Expose
    override var extension: String? = null
) : com.pppp.foo.Thumbnail, Parcelable
