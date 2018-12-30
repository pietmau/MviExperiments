package com.pppp.mvicoreapp.main.di

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideActivty(): AppCompatActivity = activity
}