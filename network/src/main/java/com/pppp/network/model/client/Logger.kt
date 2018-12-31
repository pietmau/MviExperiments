package com.pppp.network.model.client

import android.util.Log

interface Logger {
    fun e(tag: String? = "", msg: String = "")
}

class AndroidLogger : Logger {

    override fun e(tag: String?, msg: String) {
        Log.e(tag ?: DEFAULT_TAG, msg)
    }

    companion object {
        private const val DEFAULT_TAG = "Error"
    }
}
