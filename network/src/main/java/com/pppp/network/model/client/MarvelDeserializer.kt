package com.pppp.network.model.client

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSyntaxException
import com.pppp.lib.ComicsBook
import com.pppp.network.model.poko.Comics
import com.pppp.network.model.poko.NetworkComicsBook
import java.lang.reflect.Type

internal class MarvelDeserializer(private val logger: Logger = AndroidLogger()) :
    JsonDeserializer<List<ComicsBook>> {
    private var gson = Gson()
    private val TAG = MarvelDeserializer::class.simpleName

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<ComicsBook> {
        return try {
            gson.fromJson(json, Comics::class.java).data?.results ?: emptyList<NetworkComicsBook>()
        } catch (ex: JsonSyntaxException) {
            logger.e(TAG, ex.localizedMessage)
            emptyList<NetworkComicsBook>()
        }
    }
}
