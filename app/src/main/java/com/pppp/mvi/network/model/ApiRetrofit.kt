package com.marvel.marvel.main.model

import com.marvel.marvel.main.model.pojos.Comics
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import java.util.concurrent.TimeUnit

class ApiRetrofit() : Api {
    companion object {
        private const val MAIN_URL = "https://gateway.marvel.com:443/"
        private const val TIMEOUT_IN_SECONDS = 60L
    }
    private val api: Api

    override val comics: Observable<Comics>
        get() = getComics(QueryMap())


    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()
        api = retrofit.create(Api::class.java)
    }

    override fun getComics(query: Map<String, String>) = api.getComics(query)
}
