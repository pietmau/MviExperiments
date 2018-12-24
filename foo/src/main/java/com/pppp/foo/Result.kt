package com.pppp.foo

import android.os.Parcelable

interface Result:Parcelable {
    var title: String?
    var description: String?
    var pageCount: Int
    var prices: List<Price>?
    var thumbnail: Thumbnail?
    var creators: Creators<Item>?
}