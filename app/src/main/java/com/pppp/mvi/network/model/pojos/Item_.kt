package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item_(
    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null
)
