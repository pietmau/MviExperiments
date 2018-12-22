package com.pppp.mvicoreapp.main.di

import com.marvel.marvel.application.ImageLoader
import com.marvel.marvel.application.PicassoImageLoader
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideImageLoader(): ImageLoader = PicassoImageLoader
}