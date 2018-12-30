package com.pppp.network.model.poko

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("results")
    @Expose val results: List<NetworkComicsBook>? = null
)
