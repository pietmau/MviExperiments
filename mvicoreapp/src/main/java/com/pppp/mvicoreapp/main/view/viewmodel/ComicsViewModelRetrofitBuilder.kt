package com.marvel.marvel.viewmodel


import android.content.Context
import com.marvel.marvel.R
import com.marvel.marvel.main.model.pojos.Result

@Deprecated("We are not using data binding anymore, this is not longer needed")
class ComicsViewModelRetrofitBuilder {
    val title: String?
    val imageUrl: String?
    val description: String?
    val pageCount: Int
    val price: String
    val authors: String
    val priceAsString: String
    val numberOfPagesAsString: String

    constructor(item: Result, context: Context?) {
        title = item.title
        imageUrl = parseImageUrl(item)
        description = parseDescription(item)
        pageCount = parsePageCount(item)
        price = parsePrice(item)
        authors = getAuthors(item)
        priceAsString = context?.resources?.getString(R.string.price) + price
        numberOfPagesAsString = pageCount.toString() + context?.getString(R.string.pagescount)
    }

    fun build(): ComicsViewModel = ComicsViewModelRetrofit(this@ComicsViewModelRetrofitBuilder)

    private fun getAuthors(item: Result): String {
        val authorList = parseAuthors(item)
        val stringBuilder = StringBuilder()
        authorList.forEach { stringBuilder.append(it).append("\n") }
        return stringBuilder.toString()
    }

    private fun parseAuthors(result: Result) =
        result.creators?.items?.mapNotNull { it.name } ?: emptyList()

    private fun parseImageUrl(item: Result) = item.thumbnail?.path + "." + item.thumbnail?.extension

    private fun parseDescription(item: Result) = item.description

    private fun parsePageCount(item: Result) = item.pageCount

    private fun parsePrice(item: Result) =
        item.prices?.let { prices -> prices.firstOrNull()?.price } ?: "0.0"
}
