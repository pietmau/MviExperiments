package com.pppp.network.model.poko

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.lib.Item

internal data class NetworkItem(
    @SerializedName("name")
    @Expose
    override val name: String? = null
) : com.pppp.lib.Item
