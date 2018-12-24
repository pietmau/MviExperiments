package com.marvel.marvel.main.model.pojos

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TextObject(
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("language")
    @Expose
    var language: String? = null,
    @SerializedName("text")
    @Expose
    var text: String? = null
) : Parcelable

