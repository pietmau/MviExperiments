package com.pppp.mvicoreapp.main.view.viewmodel

import android.content.Context
import com.pppp.mvicoreapp.R

interface ComicsBookMapper {
    fun map(item: com.pppp.lib.ComicsBook): ComicsBookViewModel
}

class ComicsBookMapperImp(private val context: Context) :
    ComicsBookMapper {

    override fun map(item: com.pppp.lib.ComicsBook): ComicsBookViewModel {
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

    private fun getAuthors(item: com.pppp.lib.ComicsBook): String {
        val authorList = parseAuthors(item)
        val stringBuilder = StringBuilder()
        authorList.forEach { stringBuilder.append(it).append("\n") }
        return stringBuilder.toString()
    }

    private fun parseAuthors(comicsBook: com.pppp.lib.ComicsBook) =
        comicsBook.creators?.items?.mapNotNull { it.name } ?: emptyList()

    private fun parseImageUrl(item: com.pppp.lib.ComicsBook): String {
        val thumbnail = item.thumbnail
        thumbnail ?: return ""
        return thumbnail.path + "." + thumbnail.extension
    }

    private fun parsePrice(item: com.pppp.lib.ComicsBook) = item.prices?.firstOrNull()?.price ?: "0.0"
}
