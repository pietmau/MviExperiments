package com.pppp.network.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.marvel.marvel.main.model.pojos.NetworkCreators
import com.marvel.marvel.main.model.pojos.NetworkPrice
import com.marvel.marvel.main.model.pojos.NetworkThumbnail
import com.pppp.foo.Result

data class NetworkResult(
    @SerializedName("title")
    @Expose
    override var title: String? = null,

    @SerializedName("description")
    @Expose
    override var description: String? = null,

    @SerializedName("pageCount")
    @Expose
    override var pageCount: Int = 0,

    @SerializedName("prices")
    @Expose
    override var prices: List<NetworkPrice>? = null,

    @SerializedName("thumbnail")
    @Expose
    override var thumbnail: NetworkThumbnail? = null,

    @SerializedName("creators")
    @Expose
    override var creators: NetworkCreators? = null
) : Result
