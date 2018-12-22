package com.pppp.mvicoreapp.main.view.viewmodel

import android.content.Context
import com.marvel.marvel.main.model.pojos.Result
import com.pppp.mvicoreapp.R

interface ComicsBookMapper {
    fun map(item: Result): ComicsBookViewModel
}

class ComicsBookMapperImp(private val context: Context) : ComicsBookMapper {//TODO migrate to function

    override fun map(item: Result): ComicsBookViewModel {
        val title = item.title ?: ""
        val imageUrl = parseImageUrl(item)
        val description = item.description ?: ""
        val pageCount = item.pageCount
        val price = parsePrice(item)
        val authors = getAuthors(item)
        val priceAsString = context.resources?.getString(R.string.price) + price
        val numberOfPagesAsString = pageCount.toString() + context.getString(R.string.pagescount)

        return ComicsBookViewModel(
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

    private fun getAuthors(item: Result): String {
        val authorList = parseAuthors(item)
        val stringBuilder = StringBuilder()
        authorList.forEach { stringBuilder.append(it).append("\n") }
        return stringBuilder.toString()
    }

    private fun parseAuthors(result: Result) =
        result.creators?.items?.mapNotNull { it.name } ?: emptyList()

    private fun parseImageUrl(item: Result): String {
        val thumbnail = item.thumbnail
        thumbnail ?: return ""
        return thumbnail.path + "." + thumbnail.extension
    }

    private fun parsePrice(item: Result) = item.prices?.firstOrNull()?.price ?: "0.0"
}
