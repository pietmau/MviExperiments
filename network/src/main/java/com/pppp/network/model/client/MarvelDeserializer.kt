package com.pppp.network.model.client

import android.util.Log
import com.google.gson.*
import com.pppp.entities.ComicsBook
import com.pppp.network.model.poko.Comics
import com.pppp.network.model.poko.NetworkComicsBook
import java.lang.reflect.Type

internal class MarvelDeserializer : JsonDeserializer<List<ComicsBook>> {
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
            Log.e(TAG, ex.localizedMessage)
            emptyList<NetworkComicsBook>()
        }
    }
}