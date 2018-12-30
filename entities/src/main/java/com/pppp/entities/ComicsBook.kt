package com.pppp.entities

interface ComicsBook {
    val id: Int?
    val title: String?
    val description: String?
    val pageCount: Int
    val prices: List<Price>?
    val thumbnail: Thumbnail?
    val creators: Creators?
}