package com.pppp.mvicoreapp.main.di

import com.pppp.mvicoreapp.main.view.customview.PicassoImageLoader
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideImageLoader(): ImageLoader = PicassoImageLoader
}