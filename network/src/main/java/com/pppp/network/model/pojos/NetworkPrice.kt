package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.foo.Price

data class NetworkPrice(
    @SerializedName("price")
    @Expose
    override var price: String? = null
) : Price
