package com.pppp.network.model.poko

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.lib.Price

internal data class NetworkPrice(
    @SerializedName("price")
    @Expose
    override val price: String? = null
) : Price
