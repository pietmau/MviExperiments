package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.entities.Creators

data class NetworkCreators(
    @SerializedName("items")
    @Expose
    override var items: List<NetworkItem>? = null
) : Creators
