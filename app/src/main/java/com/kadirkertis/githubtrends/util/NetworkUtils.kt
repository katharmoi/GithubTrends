package com.kadirkertis.githubtrends.util

import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Network relateded utility functions
 */
@Singleton
class NetworkUtils @Inject constructor(private val cM: ConnectivityManager) {


    private val isConnectedToNetwork: Boolean
        get() {
            val activeNetworkInfo = cM.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    fun isConnected(): Boolean {
        return isConnectedToNetwork
    }

    fun isUnMetered(): Boolean {
        return cM.isActiveNetworkMetered
    }
}