package com.pppp.mvicoreapp.main.di

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideActivty(): AppCompatActivity = activity
}