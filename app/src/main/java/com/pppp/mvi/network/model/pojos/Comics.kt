package com.marvel.marvel.main.model.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comics(
    @SerializedName("code")
    @Expose
    private val code: Long = 0,
    @SerializedName("status")
    @Expose
    private val status: String? = null,
    @SerializedName("copyright")
    @Expose
    private val copyright: String? = null,
    @SerializedName("attributionText")
    @Expose
    private val attributionText: String? = null,
    @SerializedName("attributionHTML")
    @Expose
    private val attributionHTML: String? = null,
    @SerializedName("etag")
    @Expose
    private val etag: String? = null,
    @SerializedName("data")
    @Expose var data: Data? = null
)
