package com.pppp.network.model.networkchecker

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Single


internal class NetworkCheckerImpl(private val context: Context) : NetworkChecker {

    override fun isNetworkAvailable(): Single<Boolean> = Single.fromCallable { getNetworkState() }

    private fun getNetworkState(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}