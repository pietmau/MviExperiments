package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("offset")
    @Expose
    var offset: Long = 0,
    @SerializedName("limit")
    @Expose
    var limit: Long = 0,
    @SerializedName("total")
    @Expose
    var total: Long = 0,
    @SerializedName("count")
    @Expose
    var count: Long = 0,
    @SerializedName("results")
    @Expose var results: List<Result>? = null
)
