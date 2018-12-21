package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    @Expose
    var id: Long = 0,
    @SerializedName("digitalId")
    @Expose
    var digitalId: Long = 0,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("issueNumber")
    @Expose
    var issueNumber: Double = 0.toDouble(),
    @SerializedName("variantDescription")
    @Expose
    var variantDescription: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("modified")
    @Expose
    var modified: String? = null,
    @SerializedName("isbn")
    @Expose
    var isbn: String? = null,
    @SerializedName("upc")
    @Expose
    var upc: String? = null,
    @SerializedName("diamondCode")
    @Expose
    var diamondCode: String? = null,
    @SerializedName("ean")
    @Expose
    var ean: String? = null,
    @SerializedName("issn")
    @Expose
    var issn: String? = null,
    @SerializedName("format")
    @Expose
    var format: String? = null,
    @SerializedName("pageCount")
    @Expose
    var pageCount: Int = 0,
    @SerializedName("textObjects")
    @Expose
    var textObjects: List<TextObject>? = null,
    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null,
    @SerializedName("urls")
    @Expose
    var urls: List<Url>? = null,
    @SerializedName("series")
    @Expose
    var series: Series? = null,
    @SerializedName("variants")
    @Expose
    var variants: List<Variant>? = null,
    @SerializedName("collections")
    @Expose
    var collections: List<Any>? = null,
    @SerializedName("collectedIssues")
    @Expose
    var collectedIssues: List<Any>? = null,
    @SerializedName("dates")
    @Expose
    var dates: List<Date>? = null,
    @SerializedName("prices")
    @Expose
    var prices: List<Price>? = null,
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null,
    @SerializedName("images")
    @Expose
    var images: List<Image>? = null,
    @SerializedName("creators")
    @Expose
    var creators: Creators? = null,
    @SerializedName("characters")
    @Expose
    var characters: Characters? = null,
    @SerializedName("stories")
    @Expose
    var stories: Stories? = null,
    @SerializedName("events")
    @Expose
    var events: Events? = null
)
