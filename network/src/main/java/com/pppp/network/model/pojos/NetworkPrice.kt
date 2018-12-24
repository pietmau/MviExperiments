package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkPrice(
    @SerializedName("type")
    @Expose(serialize = false, deserialize = false)
    var type: String? = null,
    @SerializedName("price")
    @Expose
    override var price: String? = null
) : com.pppp.foo.Price
