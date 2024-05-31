package com.composemates.composecode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.composemates.composecode.animations.Animation
import com.composemates.composecode.networkMonitor.NetworkConnectivityHelper
import com.composemates.composecode.sliders.TestCircularSlider
import com.composemates.composecode.swipe.Swipe
import com.composemates.composecode.ui.theme.ComposeCodeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var networkConnectivityHelper: NetworkConnectivityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkConnectivityHelper.registerNetworkCallback()

        setContent {
            ComposeCodeTheme {
//                TestCircularSlider()

                Swipe()

            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        networkConnectivityHelper.unregisterNetworkCallback()
    }
}
