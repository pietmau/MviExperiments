package com.pppp.network.model.poko

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pppp.entities.ComicsBook

internal data class NetworkComicsBook(
    override val id: Int = 0,
    @SerializedName("title")
    @Expose
    override val title: String? = null,
    @SerializedName("description")
    @Expose
    override val description: String? = null,
    @SerializedName("pageCount")
    @Expose
    override val pageCount: Int = 0,
    @SerializedName("prices")
    @Expose
    override val prices: List<NetworkPrice>? = null,
    @SerializedName("thumbnail")
    @Expose
    override val thumbnail: NetworkThumbnail? = null,
    @SerializedName("creators")
    @Expose
    override val creators: NetworkCreators? = null
) : ComicsBook
