package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.foo.Creators

data class NetworkCreators(
    @SerializedName("available")
    @Expose(serialize = false, deserialize = false)
    var available: Long = 0,

    @SerializedName("collectionURI")
    @Expose(serialize = false, deserialize = false)
    var collectionURI: String? = null,

    @SerializedName("items")
    @Expose
    override var items: List<NetworkItem>? = null,

    @SerializedName("returned")
    @Expose(serialize = false, deserialize = false)
    var returned: Long = 0
) : Creators<NetworkItem>
