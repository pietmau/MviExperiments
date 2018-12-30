package com.pppp.network.model.client

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class QueryInterceptor(private val PUBLIC_KEY: String, private val PRIVATE_KEY: String) :
    Interceptor {
    private val TAG = QueryInterceptor::class.simpleName

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()
        val timestamp = System.currentTimeMillis().toString()
        val createAsh = createAsh(timestamp)
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(LIMIT_KEY, LIMIT)
            .addQueryParameter(TIMESTAMP_KEY, timestamp)
            .addQueryParameter(API_KEY, PUBLIC_KEY)
            .addQueryParameter(HASH_KEY, createAsh)
            .addQueryParameter(DATE_DESCRIPTOR_KEY, DATE_DESCRIPTOR_VALUE)
            .build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun createAsh(timestamp: String) = md5(timestamp + PRIVATE_KEY + PUBLIC_KEY)

    private fun md5(string: String): String {
        val md5 = "md5"
        try {
            // Create md5 Hash
            val digest = MessageDigest.getInstance(md5)
            digest.update(string.toByteArray())
            val messageDigest = digest.digest()
            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var hex = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (hex.length < 2)
                    hex = "0$hex"
                hexString.append(hex)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            Log.d(TAG, e.toString())
        }
        return ""
    }

    companion object {
        private const val DATE_DESCRIPTOR_KEY = "dateDescriptor"
        private const val DATE_DESCRIPTOR_VALUE = "thisMonth"
        private const val LIMIT_KEY = "limit"
        private const val LIMIT = "50"
        private const val TIMESTAMP_KEY = "ts"
        private const val API_KEY = "apikey"
        private const val HASH_KEY = "hash"
    }
}