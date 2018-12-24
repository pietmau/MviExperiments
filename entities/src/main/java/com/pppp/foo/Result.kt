package com.pppp.foo

interface Result {
    var title: String?
    var description: String?
    var pageCount: Int
    val prices: List<Price>?
    val thumbnail: Thumbnail?
    val creators: Creators?
}