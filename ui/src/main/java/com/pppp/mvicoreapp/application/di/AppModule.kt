package com.pppp.mvicoreapp.application.di

import android.app.Application
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import com.pppp.mvicoreapp.main.view.customview.PicassoImageLoader
import com.pppp.mvicoreapp.main.view.customview.Timer
import com.pppp.mvicoreapp.main.view.customview.TimerImpl
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapper
import com.pppp.mvicoreapp.main.view.viewmodel.ComicsBookMapperImp
import dagger.Module
import dagger.Provides

@Module()
class AppModule(private val application: Application) {

    @Provides
    fun provideComicsBookMapper(): ComicsBookMapper =
        ComicsBookMapperImp(application.applicationContext)

    @Provides
    fun provideImageLoader(): ImageLoader = PicassoImageLoader

    @Provides
    fun provideTimer(): Timer = TimerImpl()
}