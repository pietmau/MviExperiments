package com.pppp.network.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.marvel.marvel.main.model.pojos.*

data class NetworkResult(
    @SerializedName("id")
    @Expose(serialize = false, deserialize = false)
    var id: Long = 0,

    @SerializedName("digitalId")
    @Expose(serialize = false, deserialize = false)
    var digitalId: Long = 0,

    @SerializedName("title")
    @Expose
    override var title: String? = null,

    @SerializedName("issueNumber")
    @Expose(serialize = false, deserialize = false)
    var issueNumber: Double = 0.toDouble(),

    @SerializedName("variantDescription")
    @Expose(serialize = false, deserialize = false)
    var variantDescription: String? = null,

    @SerializedName("description")
    @Expose
    override var description: String? = null,

    @SerializedName("modified")
    @Expose(serialize = false, deserialize = false)
    var modified: String? = null,

    @SerializedName("isbn")
    @Expose(serialize = false, deserialize = false)
    var isbn: String? = null,

    @SerializedName("upc")
    @Expose(serialize = false, deserialize = false)
    var upc: String? = null,

    @SerializedName("diamondCode")
    @Expose(serialize = false, deserialize = false)
    var diamondCode: String? = null,

    @SerializedName("ean")
    @Expose(serialize = false, deserialize = false)
    var ean: String? = null,

    @SerializedName("issn")
    @Expose(serialize = false, deserialize = false)
    var issn: String? = null,

    @SerializedName("format")
    @Expose(serialize = false, deserialize = false)
    var format: String? = null,

    @SerializedName("pageCount")
    @Expose
    override var pageCount: Int = 0,

    @SerializedName("textObjects")
    @Expose(serialize = false, deserialize = false)
    var textObjects: List<TextObject>? = null,

    @SerializedName("resourceURI")
    @Expose(serialize = false, deserialize = false)
    var resourceURI: String? = null,

    @SerializedName("urls")
    @Expose(serialize = false, deserialize = false)
    var urls: List<Url>? = null,

    @SerializedName("series")
    @Expose(serialize = false, deserialize = false)
    var series: Series? = null,

    @SerializedName("variants")
    @Expose(serialize = false, deserialize = false)
    var variants: List<Variant>? = null,

    @SerializedName("collections")
    @Expose(serialize = false, deserialize = false)
    var collections: List<Any>? = null,

    @SerializedName("collectedIssues")
    @Expose(serialize = false, deserialize = false)
    var collectedIssues: List<Any>? = null,

    @SerializedName("dates")
    @Expose(serialize = false, deserialize = false)
    var dates: List<Date>? = null,

    @SerializedName("prices")
    @Expose
    override var prices: List<NetworkPrice>? = null,

    @SerializedName("thumbnail")
    @Expose
    override var thumbnail: NetworkThumbnail? = null,

    @SerializedName("images")
    @Expose(serialize = false, deserialize = false)
    var images: List<Image>? = null,

    @SerializedName("creators")
    @Expose
    override var creators: NetworkCreators? = null,

    @SerializedName("characters")
    @Expose(serialize = false, deserialize = false)
    var characters: Characters? = null,

    @SerializedName("stories")
    @Expose(serialize = false, deserialize = false)
    var stories: Stories? = null,

    @SerializedName("events")
    @Expose(serialize = false, deserialize = false)
    var events: Events? = null
) : com.pppp.foo.Result<NetworkPrice, NetworkThumbnail, NetworkCreators>
