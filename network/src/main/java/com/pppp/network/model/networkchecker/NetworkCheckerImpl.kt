package com.pppp.network.model.networkchecker

import android.content.Context
import android.net.NetworkInfo
import android.support.v4.content.ContextCompat.getSystemService
import android.net.ConnectivityManager


internal class NetworkCheckerImpl(private val context: Context) : NetworkChecker {
    override fun networkIsAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}