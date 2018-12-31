package com.pppp.network.model.client

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.pppp.network.model.Api
import com.pppp.network.model.ComicsClient
import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

internal class RetrofitClient(
    cacheDir: File,
    publicKey: String,
    privateKey: String,
    mainUrl: String
) :
    ComicsClient {
    private val api: Api

    init {
        val loggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }
        val cache = Cache(cacheDir, 1024 * 1024)
        val client = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(QueryInterceptor(publicKey, privateKey))
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(mainUrl)
            .client(client)
            .addConverterFactory(gsonConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        api = retrofit.create(Api::class.java)
    }

    override fun getComics(): Observable<List<com.pppp.lib.ComicsBook>> = api.getComics()

    private fun gsonConverterFactory(): GsonConverterFactory {
        val type = object : TypeToken<MutableList<com.pppp.lib.ComicsBook>>() {}.type
        val gson =
            GsonBuilder().registerTypeAdapter(type, MarvelDeserializer()).create()
        return GsonConverterFactory.create(gson)
    }

    companion object {
        private const val TIMEOUT_IN_SECONDS = 60L
    }
}
