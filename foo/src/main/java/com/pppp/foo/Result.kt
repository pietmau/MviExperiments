package com.pppp.foo

interface Result<P : Price, T : Thumbnail, C : Creators<*>> {
    var title: String?
    var description: String?
    var pageCount: Int
    var prices: List<P>?
    var thumbnail: T?
    var creators: C?
}