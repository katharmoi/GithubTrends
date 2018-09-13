package com.kadirkertis.githubtrends.di.application

import android.content.Context
import com.kadirkertis.githubtrends.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideApplication(app : App): Context = app

}