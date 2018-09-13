package com.kadirkertis.githubtrends.di.application.shared

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Providers for Utility Objects
 */
@Module
class UtilsModule {

    @Singleton
    @Provides
    fun provideConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}