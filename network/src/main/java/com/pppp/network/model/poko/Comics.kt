package com.pppp.network.model.poko

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class Comics(
    @SerializedName("data")
    @Expose
    val data: Data? = null
)
