package com.pppp.network.model.networkchecker

import io.reactivex.Single

interface NetworkChecker {
    fun isNetworkAvailable(): Single<Boolean>
}
