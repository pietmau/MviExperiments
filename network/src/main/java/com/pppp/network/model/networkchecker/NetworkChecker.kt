package com.pppp.network.model.networkchecker

import android.content.Context
import io.reactivex.Single

interface NetworkChecker {
    fun isNetworkAvailable(): Single<Boolean>

    companion object {
        fun checker(context: Context) = NetworkCheckerImpl(context) as NetworkChecker
    }
}
