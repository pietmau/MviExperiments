package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.network.model.poko.NetworkComicsBook

data class Data(
    @SerializedName("results")
    @Expose val results: List<NetworkComicsBook>? = null
)
