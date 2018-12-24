package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.foo.Item

data class NetworkItem(
    @SerializedName("resourceURI")
    @Expose(serialize = false, deserialize = false)
    var resourceURI: String? = null,

    @SerializedName("name")
    @Expose
    override var name: String? = null,

    @SerializedName("role")
    @Expose(serialize = false, deserialize = false)
    var role: String? = null
) : Item
