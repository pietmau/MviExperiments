package com.pppp.mvicoreapp.main.view.viewmodel

import android.content.Context
import com.pppp.lib.ComicsBook
import com.pppp.mvicoreapp.R

interface ComicsBookMapper {
    fun map(item: ComicsBook): ComicsBookViewModel
}

class ComicsBookMapperImp(private val context: Context) :
    ComicsBookMapper {

    override fun map(item: ComicsBook): ComicsBookViewModel {
        val id = item.id.toString()
        val title = item.title ?: ""
        val imageUrl = parseImageUrl(item)
        val description = item.description ?: ""
        val pageCount = item.pageCount
        val price = parsePrice(item)
        val authors = getAuthors(item)
        val priceAsString = context.resources?.getString(R.string.price) + price
        val numberOfPagesAsString = pageCount.toString() + context.getString(R.string.pagescount)

        return ComicsBookViewModel(
            id,
            title,
            imageUrl,
            description,
            pageCount,
            price,
            authors,
            priceAsString,
            numberOfPagesAsString
        )
    }

    private fun getAuthors(item: ComicsBook) = parseAuthors(item).joinToString(separator = "\n")

    private fun parseAuthors(comicsBook: ComicsBook) = comicsBook.creators?.items?.mapNotNull { it.name } ?: emptyList()

    private fun parseImageUrl(item: ComicsBook): String {
        val thumbnail = item.thumbnail
        thumbnail ?: return ""
        return thumbnail.path + "." + thumbnail.extension
    }

    private fun parsePrice(item: ComicsBook) = item.prices?.firstOrNull()?.price ?: "0.0"
}
