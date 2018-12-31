package com.pppp.mvicoreapp.main.di

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ActivityModule(private val activity: AppCompatActivity, private val comicId: Int = -1) {

    @Provides
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    @Named(COMIC_ID)
    fun provideComicId(): Int = comicId

    companion object {
        const val COMIC_ID = "comics_id"
    }
}