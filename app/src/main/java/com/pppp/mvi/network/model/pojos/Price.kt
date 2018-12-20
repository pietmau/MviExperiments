package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("price")
    @Expose
    var price: String? = null
)
