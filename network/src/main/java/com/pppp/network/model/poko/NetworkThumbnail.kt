package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.entities.Thumbnail


data class NetworkThumbnail(
    @SerializedName("path")
    @Expose
    override var path: String? = null,
    @SerializedName("extension")
    @Expose
    override var extension: String? = null
) : Thumbnail
