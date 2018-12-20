package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null
)
