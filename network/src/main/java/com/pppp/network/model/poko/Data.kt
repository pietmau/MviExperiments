package com.pppp.network.model.poko

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class Data(
    @SerializedName("results")
    @Expose val results: List<NetworkComicsBook>? = null
)
