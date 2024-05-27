package com.composemates.composecode.networkMonitor

import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.util.Log
import javax.inject.Inject

class NetworkConnectivityHelper @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val networkCallbackHandler: NetworkCallbackHandler
) {

    fun registerNetworkCallback() {
        val networkRequest = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallbackHandler)
    }

    fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallbackHandler)
        Log.d("Network: ", "Unregistered")
    }
}