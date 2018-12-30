package com.pppp.network.model.client

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.marvel.marvel.main.model.Api
import com.pppp.entities.ComicsBook
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

class RetrofitClient(cacheDir: File, PUBLIC_KEY: String, PRIVATE_KEY: String, MAIN_URL: String) :
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
            .addInterceptor(QueryInterceptor(PUBLIC_KEY, PRIVATE_KEY))
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        api = retrofit.create(Api::class.java)
    }

    override fun getComics(): Observable<List<ComicsBook>> = api.getComics()

    private fun gsonConverterFactory(): GsonConverterFactory {
        val type = object : TypeToken<MutableList<ComicsBook>>() {}.getType()
        val gson =
            GsonBuilder().registerTypeAdapter(type, MarvelDeserializer()).create()
        return GsonConverterFactory.create(gson)
    }

    companion object {
        private const val TIMEOUT_IN_SECONDS = 60L
    }

}
