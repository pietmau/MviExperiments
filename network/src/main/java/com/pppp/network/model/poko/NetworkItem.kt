package com.pppp.network.model.poko

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.entities.Item

internal data class NetworkItem(
    @SerializedName("name")
    @Expose
    override val name: String? = null
) : Item
