package com.pppp.network.model.poko

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.lib.Creators

internal data class NetworkCreators(
    @SerializedName("items")
    @Expose
    override val items: List<NetworkItem>? = null
) : com.pppp.lib.Creators
