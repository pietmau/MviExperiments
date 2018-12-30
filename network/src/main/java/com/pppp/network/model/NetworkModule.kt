package com.pppp.network.model

import android.content.Context
import com.pppp.network.BuildConfig
import com.pppp.network.model.client.RetrofitClient
import com.pppp.network.model.networkchecker.NetworkChecker
import com.pppp.network.model.networkchecker.NetworkCheckerImpl
import dagger.Module
import dagger.Provides
import java.io.File

@Module
class NetworkModule {

    @Provides
    fun provideClient(cacheDir: File): ComicsClient =
        RetrofitClient(
            cacheDir,
            BuildConfig.PUBLIC_KEY,
            BuildConfig.PRIVATE_KEY,
            BuildConfig.MAIN_URL
        )

    @Provides
    fun provideChecker(context: Context): NetworkChecker = NetworkCheckerImpl(context)
}