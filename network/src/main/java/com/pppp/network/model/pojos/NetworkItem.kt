package com.marvel.marvel.main.model.pojos

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.foo.Item
import kotlinx.android.parcel.Parcelize

data class NetworkItem(
    @SerializedName("name")
    @Expose
    override var name: String? = null
    ) : Item
