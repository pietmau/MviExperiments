package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.entities.Item

data class NetworkItem(
    @SerializedName("name")
    @Expose
    override var name: String? = null
    ) : Item
