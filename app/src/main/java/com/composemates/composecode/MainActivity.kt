package com.composemates.composecode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.composemates.composecode.navigation.Navigate
import com.composemates.composecode.networkMonitor.NetworkConnectivityHelper
import com.composemates.composecode.screens.Login
import com.composemates.composecode.swipe.SwipeText
import com.composemates.composecode.ui.theme.ComposeCodeTheme
import com.composemates.composecode.viewModels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var networkConnectivityHelper: NetworkConnectivityHelper

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkConnectivityHelper.registerNetworkCallback()

        setContent {
            ComposeCodeTheme {

//                Navigate()
//                Login(authViewModel)
              SwipeText()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        networkConnectivityHelper.unregisterNetworkCallback()
    }
}
