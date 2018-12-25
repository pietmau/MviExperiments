package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.entities.Thumbnail


data class NetworkThumbnail(
    @SerializedName("path")
    @Expose
    override val path: String? = null,
    @SerializedName("extension")
    @Expose
    override val extension: String? = null
) : Thumbnail
