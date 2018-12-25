package com.pppp.network.model.networkchecker

import android.content.Context

interface NetworkChecker {
    fun networkIsAvailable(): Boolean

    companion object {
        fun checker(context: Context) = NetworkCheckerImpl(context) as NetworkChecker
    }
}
