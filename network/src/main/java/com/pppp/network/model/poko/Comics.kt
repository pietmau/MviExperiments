package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comics(
    @SerializedName("data")
    @Expose
    val data: Data? = null
)
