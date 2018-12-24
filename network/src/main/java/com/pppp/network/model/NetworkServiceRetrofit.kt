package com.marvel.marvel.main.model

import com.marvel.marvel.main.model.pojos.Comics
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkServiceRetrofit() : NetworkService {
    companion object {
        private const val MAIN_URL = "https://gateway.marvel.com:443/"
        private const val TIMEOUT_IN_SECONDS = 60L
    }

    private val networkService: NetworkService

    override val comics: Observable<Comics>
        get() = getComics(QueryMap())

    init {
        val interceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        //val cache = Cache(context.cacheDir, 1024 * 1024)
        val client = OkHttpClient.Builder()
          //  .cache(cache)
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        networkService = retrofit.create(NetworkService::class.java)
    }

    override fun getComics(query: Map<String, String>) = networkService.getComics(query)
}
