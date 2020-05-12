package com.aubay.pokemon.core.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

interface Connectivity {
    fun hasNetworkAccess(): Boolean
}

class ConnectivityImpl(private val context: Context) : Connectivity {

    override fun hasNetworkAccess(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true
        return isConnected
    }

}