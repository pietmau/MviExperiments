package com.pppp.entities

interface Result {
    var id: Int?
    var title: String?
    var description: String?
    var pageCount: Int
    val prices: List<Price>?
    val thumbnail: Thumbnail?
    val creators: Creators?
}