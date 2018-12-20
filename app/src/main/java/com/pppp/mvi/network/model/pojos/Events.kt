package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("available")
    @Expose
    var available: Long = 0,
    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String? = null,
    @SerializedName("items")
    @Expose
    var items: List<Any>? = null,
    @SerializedName("returned")
    @Expose
    var returned: Long = 0
)
