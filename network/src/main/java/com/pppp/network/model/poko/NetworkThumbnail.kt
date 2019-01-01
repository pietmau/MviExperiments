package com.pppp.network.model.poko

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.lib.Thumbnail

internal data class NetworkThumbnail(
    @SerializedName("path")
    @Expose
    override val path: String? = null,
    @SerializedName("extension")
    @Expose
    override val extension: String? = null
) : Thumbnail
