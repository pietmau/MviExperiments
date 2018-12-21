package com.marvel.marvel.main.model


import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

internal class QueryMap : LinkedHashMap<String, String>() {

    companion object {
        private const val PUBLIC_KEY = "c735e50e07fb1d2384b908dea2ef423a" //TODO inject and move to environment variable
        private const val PRIVATE_KEY = "139ad61ebc977dc1dbdfb84f26f11d6f9985dd29"//TODO inject and move to environment variable
        private const val DATE_DESCRIPTOR_KEY = "dateDescriptor"
        private const val DATE_DESCRIPTOR_VALUE = "thisMonth"
        private val TAG = QueryMap::class.simpleName
        private const val LIMIT_KEY = "limit"
        private const val LIMIT = "100"
        private const val TIMESTAMP_KEY = "ts"
        private const val API_KEY = "apikey"
        private const val HASH_KEY = "hash"
    }

    init {
        val timestamp = System.currentTimeMillis().toString()
        val hash = md5(timestamp + PRIVATE_KEY + PUBLIC_KEY)
        put(LIMIT_KEY, LIMIT)
        put(TIMESTAMP_KEY, timestamp)
        put(API_KEY, PUBLIC_KEY)
        put(HASH_KEY, hash)
        put(DATE_DESCRIPTOR_KEY, DATE_DESCRIPTOR_VALUE)
    }

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
}
